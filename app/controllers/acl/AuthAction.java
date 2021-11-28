package controllers.acl;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class AuthAction extends Action.Simple {

    private DynamoDbClient ddb;
    static final int DAY_IN_MILLIS = 86400000;

    public AuthAction() {
        ddb = DynamoDbClient.builder()
                .region(Region.US_WEST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("AKIA2LHT56OXDESCZC57", "H48xwHNVC++93NT/BK04bJJSUkg9t3Sd1bray9qg")))
                .build();
    }

    public CompletionStage<Result> call(Http.Request req) {
        System.out.println(req);

        Optional<String> access_token = req.queryString("access_token");
        if (access_token.isPresent()) {
            Optional<Map<String, AttributeValue>> dynamoItem = DynamoDBCli.getDynamoDBItem(ddb, "sessions", "access_token", access_token.get());

            if( dynamoItem.isPresent() ) {
                Session session = new Session(dynamoItem.get());
                long lastRefresh = Long.parseLong(session.lastRefresh);
                long currentMillis = System.currentTimeMillis();
                if (currentMillis - lastRefresh < DAY_IN_MILLIS) {
                    DynamoDBCli.putItemInTable(ddb, "sessions", "access_token", session.accessToken, "last_refresh", String.valueOf(currentMillis));
                    System.out.println("Authorized");
                    return delegate.call(req);
                }
            }
        }
        System.out.println("Unauthorized");
        return delegate.call(req);
    }

    class Session {
        public String accessToken;
        public String lastRefresh;

        Session(Map<String, AttributeValue> map) {
            this.accessToken = map.get("access_token").s();
            this.lastRefresh = map.get("last_refresh").n();
        }
    }
}

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
    static final int ONE_DAY_IN_MILLIS = 86400000;

    public AuthAction() {
        ddb = DynamoDbClient.builder()
                .region(Region.US_WEST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        System.getenv("AWS_ACCESS_KEY_ID"),
                                        System.getenv("AWS_SECRET_ACCESS_KEY")
                                )
                        )
                ).build();
    }

    public CompletionStage<Result> call(Http.Request req) {
        System.out.println(req);
        Optional<String> access_token = req.queryString("access_token");

        if (access_token.isPresent()) {
            Optional<Map<String, AttributeValue>> dynamoItem = DynamoDBCli.getDynamoDBItem(ddb, "sessions", "access_token", access_token.get());

            if (dynamoItem.isPresent()) {
                Session session = new Session(dynamoItem.get());
                long lastRefresh = Long.parseLong(session.getLastRefresh());
                long currentMillis = System.currentTimeMillis();

                if (currentMillis - lastRefresh < ONE_DAY_IN_MILLIS) {

                    DynamoDBCli.putItemInTable(ddb, "sessions",
                            "access_token", session.getAccessToken(),
                            "last_refresh", String.valueOf(currentMillis),
                            "name", session.getUser().getName(),
                            "telephone", session.getUser().getTelephone());

                    return delegate.call(req.addAttr(Attrs.USER, session.getUser()));
                }
            }
        }
        return delegate.call(req);
    }

}

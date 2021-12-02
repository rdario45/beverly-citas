package controllers.acl;

import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DynamoDBCli {

    public static Optional<Map<String, AttributeValue>> getDynamoDBItem(DynamoDbClient ddb,
                                                                        String tableName,
                                                                        String key,
                                                                        String keyVal) {

        HashMap<String,AttributeValue> keyToGet = new HashMap<>();

        keyToGet.put(key, AttributeValue.builder().s(keyVal).build());

        GetItemRequest request = GetItemRequest.builder()
                .key(keyToGet)
                .tableName(tableName)
                .build();

        try {
            Map<String,AttributeValue> returnedItem = ddb.getItem(request).item();

            if (!returnedItem.isEmpty()) {
                return Optional.of(returnedItem);
            } else {
                System.out.format("No item found with the key %s!\n", key);
                return Optional.empty();
            }
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }

    public static void putItemInTable(DynamoDbClient ddb,
                                      String tableName,
                                      String key,
                                      String keyVal,
                                      String lastRefresh,
                                      String lastRefreshValue,
                                      String name,
                                      String nameValue,
                                      String telephone,
                                      String telephoneNumber){

        HashMap<String,AttributeValue> itemValues = new HashMap<>();

        itemValues.put(key, AttributeValue.builder().s(keyVal).build());
        itemValues.put(lastRefresh, AttributeValue.builder().n(lastRefreshValue).build());
        itemValues.put(name, AttributeValue.builder().s(nameValue).build());
        itemValues.put(telephone, AttributeValue.builder().s(telephoneNumber).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(itemValues)
                .build();

        try {
            ddb.putItem(request);
        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The Amazon DynamoDB table \"%s\" can't be found.\n", tableName);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
        }
    }
}

package mapper;

import acl.DynamoMapper;
import domain.Client;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.Optional;

public class ClientMapper implements DynamoMapper<Client> {

    public Client map(Map<String, AttributeValue> map) {
        return new Client(
                Optional.ofNullable(map.get("id")).map(AttributeValue::s).orElse("0"),
                Optional.ofNullable(map.get("name")).map(AttributeValue::s).orElse("undefined"),
                Optional.ofNullable(map.get("address")).map(AttributeValue::s).orElse("undefined"),
                Optional.ofNullable(map.get("phone")).map(AttributeValue::s).orElse("undefined")
        );
    }
}

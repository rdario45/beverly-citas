package mapper;

import acl.DynamoMapper;
import domain.Clienta;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientaMapper implements DynamoMapper<Clienta> {

    public Clienta map(Map<String, AttributeValue> map) {
        return new Clienta(
                Optional.ofNullable(map.get("id")).map(AttributeValue::s).orElse("undefined"),
                Optional.ofNullable(map.get("name")).map(AttributeValue::s).orElse("undefined"),
                Optional.ofNullable(map.get("phone")).map(AttributeValue::s).orElse("undefined")
        );
    }

    @Override
    public List<Clienta> map(List<AttributeValue> list) {
        return list.stream().map(AttributeValue::m).map(this::map).collect(Collectors.toList());
    }
}

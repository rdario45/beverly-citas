package repository;

import acl.BeverlyDynamoDB;
import domain.Cita;
import mapper.CitaMapper;
import org.joda.time.DateTime;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Optional;

public class CitaRepository {

    public Optional<Cita> findFirst(String id) {
        long millisInit = new DateTime("2022-01-01T05:00:00.000-05:00").getMillis();
        long millisEnd = new DateTime("2022-12-31T05:00:00.000-05:00").getMillis();
        HashMap<String, AttributeValue> values = new HashMap<>();
        values.put(":id", AttributeValue.builder().s(id).build());
        values.put(":horaInicial", AttributeValue.builder().n(""+millisInit).build());
        values.put(":horaFinal", AttributeValue.builder().n("" + millisEnd).build());
        return BeverlyDynamoDB.getFirst("citas", "id = :id AND hora BETWEEN :horaInicial AND :horaFinal", values)
                .map(valueMap -> new CitaMapper().map(valueMap));
    }

    public Cita save(Cita cita) {
        return BeverlyDynamoDB.putItem("citas", cita);
    }

    public void remove(String id) {
        BeverlyDynamoDB.removeItem("citas", "id", id);
    }
}

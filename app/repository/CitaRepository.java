package repository;

import acl.BeverlyDynamoDB;
import domain.Cita;
import mapper.CitaMapper;

import java.util.Optional;

public class CitaRepository {

    public Optional<Cita> find(String id) {
        return BeverlyDynamoDB.getItem("citas", "id", id)
                .map(valueMap -> new CitaMapper().map(valueMap));
    }

    public Cita save(Cita cita) {
        return BeverlyDynamoDB.putItem("citas", cita);
    }

    public void remove(String id) {
        BeverlyDynamoDB.removeItem("citas", "id", id);
    }
}

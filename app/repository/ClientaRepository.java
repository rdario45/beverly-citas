package repository;

import acl.BeverlyDB;
import domain.Clienta;
import mapper.ClientaMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientaRepository {

  public Optional<Clienta> find(Integer id) {
    return BeverlyDB.getItem("clientas", "id", id.toString())
            .map(valueMap -> new ClientaMapper().map(valueMap));
  }

  public List<Clienta> findAll() {
    return BeverlyDB.getAll("clientas").stream()
            .map(valueMap -> new ClientaMapper().map(valueMap))
            .collect(Collectors.toList());
  }

  public Clienta save(Clienta clienta) {
    return BeverlyDB.putItem("clientas", clienta);
  }

}

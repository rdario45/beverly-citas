package repository;

import acl.BeverlyRepo;
import acl.DynamoDB;
import com.google.inject.Inject;
import domain.Client;
import mapper.ClientMapper;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientRepository {

  private DynamoDbClient ddb;

  @Inject
  public ClientRepository(BeverlyRepo beverlyRepo) {
    this.ddb = beverlyRepo.getDdb();

  }

  public Optional<Client> find(Integer id) {
    return DynamoDB.getItem( ddb, "clients", "id", id.toString())
            .map(valueMap -> new ClientMapper().map(valueMap));
  }

  public List<Client> findAll() {
    return DynamoDB.getAll(ddb, "clients").stream()
            .map(valueMap -> new ClientMapper().map(valueMap))
            .collect(Collectors.toList());
  }

  public Client save(Client client) {
    return DynamoDB.putItem(ddb, "clients", client);
  }

//  public List<Client> getAllReferred(Integer clientId) {
//    return db.onDemand(ClientSQL.class).getAllReferred(clientId);
//  }
//
//  public void update(Client client){
//    db.onDemand(ClientSQL.class).update(client);
//  }

}

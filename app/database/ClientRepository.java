package database;

import com.google.inject.Inject;
import database.sql.ClientSQL;
import domain.Client;
import org.skife.jdbi.v2.DBI;
import play.api.db.Database;

import java.util.List;

public class ClientRepository {

  private DBI db;

  @Inject
  public ClientRepository(Database db) {
    this.db = new DBI(db.dataSource());
  }

  public List<Client> listAll() {
    return db.onDemand(ClientSQL.class).listAll();
  }

  public Client find(Integer id) {
    return db.onDemand(ClientSQL.class).find(id);
  }

  public Client save(Client client) {
    return db.onDemand(ClientSQL.class).insert(client);
  }

  public List<Client> getAllReferred(Integer clientId) {
    return db.onDemand(ClientSQL.class).getAllReferred(clientId);
  }

  public void update(Client client){
    db.onDemand(ClientSQL.class).update(client);
  }

}

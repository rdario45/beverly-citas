package database;

import com.google.inject.Inject;
import database.sql.ClientDAO;
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
    return db.onDemand(ClientDAO.class).listAll();
  }

  public Client find(Integer id) {
    return db.onDemand(ClientDAO.class).find(id);
  }

  public void save(Client client) {
    db.onDemand(ClientDAO.class).insert(client);
  }

  public List<Client> getAllReferred(Integer clientId) {
    return db.onDemand(ClientDAO.class).getAllReferred(clientId);
  }

  public void update(Client client){
    db.onDemand(ClientDAO.class).update(client);
  }
//
//  public Future<Option<Event>> delete(int id){
//    return Future.of(() -> Option.of(db.onDemand(EventDAO.class).delete(id)).map(EventMapper::recordToEvent));
//  }

}

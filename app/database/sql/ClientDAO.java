package database.sql;

import domain.Client;
import database.mapper.ClientMapperDAO;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ClientMapperDAO.class)
public interface ClientDAO {

    @SqlQuery("SELECT * FROM beverly_db.clients")
    List<Client> listAll();

    @SqlQuery("SELECT * FROM beverly_db.clients WHERE id = :id")
    Client find(@Bind("id") int id);

    @SqlUpdate("INSERT INTO beverly_db.clients " +
            "(name, " +
            "address, " +
            "telephone, " +
            "referred, " +
            "discount," +
            "status) " +
            "VALUES(" +
            ":r.name, " +
            ":r.address, " +
            ":r.telephone, " +
            ":r.referred, " +
            ":r.discount," +
            ":r.status)")
    void insert(@BindBean("r") Client record);

    @SqlQuery("SELECT * FROM beverly_db.clients WHERE referred = :id LIMIT 3")
    List<Client> getAllReferred(@Bind("id") int id);

    @SqlUpdate("UPDATE beverly_db.clients SET " +
            "discount = :r.discount " +
            "WHERE id = :r.id")
    void update(@BindBean("r") Client record);
//
//    @SqlQuery("DELETE FROM events WHERE id = :id  RETURNING *")
//    Client delete(@Bind("id") int id);

}

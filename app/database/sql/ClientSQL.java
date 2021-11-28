package database.sql;

import database.mapper.ClientMapperSQL;
import domain.Client;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ClientMapperSQL.class)
public interface ClientSQL {

    @SqlQuery("SELECT * FROM clients")
    List<Client> listAll();

    @SqlQuery("SELECT * FROM clients WHERE id = :id")
    Client find(@Bind("id") int id);

    @SqlQuery("INSERT INTO clients (name, address, telephone, referred, discount,status) " +
            "VALUES(:r.name, :r.address, :r.telephone, :r.referred, :r.discount,:r.status) RETURNING *")
    Client insert(@BindBean("r") Client record);

    @SqlQuery("SELECT * FROM clients WHERE referred = :id LIMIT 3")
    List<Client> getAllReferred(@Bind("id") int id);

    @SqlUpdate("UPDATE clients SET " +
            "discount = :r.discount " +
            "WHERE id = :r.id")
    void update(@BindBean("r") Client record);

}

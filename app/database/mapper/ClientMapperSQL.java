package database.mapper;

import domain.Client;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapperSQL implements ResultSetMapper<Client> {
    @Override
    public Client map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Client(
                r.getInt("id"),
                r.getString("name"),
                r.getString("address"),
                r.getString("telephone"),
                r.getInt("referred"),
                r.getDouble("discount"),
                r.getString("status")
        );
    }
}

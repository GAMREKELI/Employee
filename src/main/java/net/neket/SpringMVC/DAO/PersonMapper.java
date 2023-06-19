package net.neket.SpringMVC.DAO;

import net.neket.SpringMVC.entity.Persone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Persone> {
    @Override
    public Persone mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Persone persone = new Persone();

        persone.setID(resultSet.getInt("id"));
        persone.setFirstName(resultSet.getString("firstName"));
        persone.setLastName(resultSet.getString("lastName"));
        persone.setAge(resultSet.getInt("age"));
        persone.setDepartament(resultSet.getString("departament"));
        persone.setEmail(resultSet.getString("email"));

        return persone;
    }
}

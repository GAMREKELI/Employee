package net.neket.SpringMVC.DAO;

import net.neket.SpringMVC.entity.Persone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Persone> {

    @Override
    public Persone mapRow(ResultSet rs, int rowNum) throws SQLException {
        Persone persone = new Persone();

        persone.setID(rs.getInt("id"));
        persone.setFirstName(rs.getString("firstName"));
        persone.setLastName(rs.getString("lastName"));
        persone.setAge(rs.getInt("age"));
        persone.setDepartament(rs.getString("departament"));
        persone.setEmail(rs.getString("email"));

        return persone;
    }
}

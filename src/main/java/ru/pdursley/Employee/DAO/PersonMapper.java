package ru.pdursley.Employee.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.pdursley.Employee.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setID(rs.getInt("id"));
        person.setFirstName(rs.getString("firstName"));
        person.setLastName(rs.getString("lastName"));
        person.setAge(rs.getInt("age"));
        person.setDepartament(rs.getString("departament"));
        person.setEmail(rs.getString("email"));

        return person;
    }
}

package ru.pdursley.Employee.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.pdursley.Employee.entity.Permission;
import ru.pdursley.Employee.entity.PersonInfo;
import ru.pdursley.Employee.model.Person;
import ru.pdursley.Employee.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Permission> {

    @Override
    public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setID(rs.getInt(1));
        person.setLogin(rs.getString("login"));
        person.setPassword(rs.getString("password"));

        PersonInfo personInfo = new PersonInfo();
        personInfo.setID(rs.getInt(4));
        personInfo.setFirstName(rs.getString("firstName"));
        personInfo.setLastName(rs.getString("lastName"));
        personInfo.setAge(rs.getInt("age"));
        personInfo.setDepartment(rs.getString("department"));
        personInfo.setEmail(rs.getString("email"));

        Role role = new Role();
        role.setId(rs.getInt(1));
        role.setRole(rs.getString("role"));

        return new Permission(person, personInfo, role);
    }
}

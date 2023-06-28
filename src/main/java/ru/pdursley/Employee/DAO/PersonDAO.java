package ru.pdursley.Employee.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.pdursley.Employee.entity.Permission;
import ru.pdursley.Employee.entity.PersonInfo;
import ru.pdursley.Employee.model.Person;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Permission> getAll() {
        String SQL = "SELECT p.*, pi.*, r.role FROM Person p " +
                "JOIN PersonInfo pi ON p.id = pi.id " +
                "JOIN Permission pm ON p.id = pm.person_id " +
                "JOIN Role r ON pm.role_id = r.id " +
                "ORDER BY p.id ASC";
        return jdbcTemplate.query(SQL, new PersonMapper());
    }

    public void addPerson(Permission permission) {

        String personSQL = "INSERT INTO Person(login, password) VALUES(?, ?)";
        KeyHolder personKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(personSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, permission.getPerson().getLogin());
            ps.setString(2, permission.getPerson().getPassword());
            return ps;
        }, personKeyHolder);

        int personId = Objects.requireNonNull(personKeyHolder.getKey()).intValue();

        String personInfoSQL = "INSERT INTO PersonInfo(person_id, firstName, lastName, age, department, email)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(personInfoSQL, personId,
                permission.getPersonInfo().getFirstName(), permission.getPersonInfo().getLastName(),
                permission.getPersonInfo().getAge(), permission.getPersonInfo().getDepartment(),
                permission.getPersonInfo().getEmail());

        String getRoleIdSQL = "SELECT id FROM Role WHERE role = ?";
        Integer roleId = jdbcTemplate.queryForObject(getRoleIdSQL, Integer.class, permission.getRole().getRole());

        if (roleId != null) {
            String permissionSQL = "INSERT INTO Permission(person_id, role_id) VALUES (?, ?)";
            jdbcTemplate.update(permissionSQL, personId, roleId);
        }
        else {
            String NullgetRoleIdSQL = "SELECT id FROM Role WHERE role = 'USER'";
            Integer NullroleId = jdbcTemplate.queryForObject(NullgetRoleIdSQL, Integer.class, permission.getRole().getRole());
            String permissionSQL = "INSERT INTO Permission(person_id, role_id) VALUES (?, ?)";
            jdbcTemplate.update(permissionSQL, personId, NullroleId);
        }
    }

    public Permission getPerson(int id) {
        String SQL = "SELECT p.*, pi.*, r.role FROM Person p " +
                "JOIN PersonInfo pi ON p.id = pi.id " +
                "JOIN Permission pm ON p.id = pm.person_id " +
                "JOIN Role r ON pm.role_id = r.id " +
                "WHERE pm.person_id = ?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void deletePerson(int id) {
        String personInfoSQL = "DELETE FROM PersonInfo WHERE person_id = ?";
        jdbcTemplate.update(personInfoSQL, id);

        String permissionSQL = "DELETE FROM Permission WHERE person_id = ?";
        jdbcTemplate.update(permissionSQL, id);

        String personSQL = "DELETE FROM Person WHERE id = ?";
        jdbcTemplate.update(personSQL, id);
    }

    public void update(Permission permission, int id) {
        String personInfoSQL = "UPDATE PersonInfo SET firstName = ?, lastName = ?, age = ?," +
                "department = ?, email = ?" +
                "WHERE person_id = ?";
        jdbcTemplate.update(personInfoSQL, permission.getPersonInfo().getFirstName(), permission.getPersonInfo().getLastName(),
                permission.getPersonInfo().getAge(), permission.getPersonInfo().getDepartment(),
                permission.getPersonInfo().getEmail(), id);

        String permissionSQL = "UPDATE Permission SET role_id = ? WHERE person_id = ?";
        String getRoleIdSQL = "SELECT id FROM Role WHERE role = ?";
        Integer roleId = jdbcTemplate.queryForObject(getRoleIdSQL, Integer.class, permission.getRole().getRole());
        jdbcTemplate.update(permissionSQL, roleId, id);

        String personSQL = "UPDATE Person SET login = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(personSQL, permission.getPerson().getLogin(), permission.getPerson().getPassword(), id);


        
    }
}

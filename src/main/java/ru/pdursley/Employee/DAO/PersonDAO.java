package ru.pdursley.Employee.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pdursley.Employee.model.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public void addPerson(Person persone) {
        jdbcTemplate.update("INSERT INTO Person(firstName, lastName, age, departament, email) VALUES(?, ?, ?, ?, ?)",
                persone.getFirstName(), persone.getLastName(), persone.getAge(), persone.getDepartament(), persone.getEmail());
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE Person SET firstName = ?, lastName = ?, age = ?, departament = ?, email = ? WHERE id = ?",
                person.getFirstName(), person.getLastName(), person.getAge(), person.getDepartament(), person.getEmail(), id);
    }
}

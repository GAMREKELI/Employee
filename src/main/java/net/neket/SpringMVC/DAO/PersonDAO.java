package net.neket.SpringMVC.DAO;

import net.neket.SpringMVC.entity.Persone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Persone> getAll() {
        List<Persone> people = new ArrayList<Persone>();
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public void addPersone(Persone persone) {
        jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?, ?, ?)",
                persone.getID(), persone.getFirstName(), persone.getLastName(), persone.getAge(), persone.getDepartament(), persone.getEmail());
    }

    public Persone getPersone(int id) {
        Persone persone = null;
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void deletePersone(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }

    public void update(Persone persone, int id) {
        jdbcTemplate.update("UPDATE Person SET firstName = ?, lastName = ?, age = ?, departament = ?, email = ? WHERE id = ?",
                persone.getFirstName(), persone.getLastName(), persone.getAge(), persone.getDepartament(), persone.getEmail(), id);
    }
}

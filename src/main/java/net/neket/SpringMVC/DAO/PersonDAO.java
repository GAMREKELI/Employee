package net.neket.SpringMVC.DAO;

import net.neket.SpringMVC.entity.Persone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
//    private List<Persone> listpersone;
    private static int ID;

//    @Value("${spring.datasource.url}")
    private static final String URL = "jdbc:mysql://localhost:3306/javaTest";

//    @Value("${spring.datasource.username}")
    private static final String USERNAME = "pdursley";

//    @Value("${spring.datasource.password}")
    private static final String PASSWORD = "Q1234qwe";

    private static final Connection connection;
    static { // Драйвер для работы с базой данных
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Persone> getAll() {
        List<Persone> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(); // Объект 'запрос' к БД
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Persone persone = new Persone();

                persone.setID(resultSet.getInt("id"));
                persone.setFirstName(resultSet.getString("firstName"));
                persone.setLastName(resultSet.getString("lastName"));
                persone.setAge(resultSet.getInt("age"));
                persone.setDepartament(resultSet.getString("departament"));
                persone.setEmail(resultSet.getString("email"));

                people.add(persone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public void addPersone(Persone persone) {
//        persone.setID(++ID);
//        listpersone.add(persone);
    }

//    public Persone getPersone(int id) {
//        return listpersone.stream().filter(p -> p.getID() == id).findAny().orElse(null);
//    }

//    public void deletePersone(int id) {
//        listpersone.removeIf(p -> p.getID() == id);
//    }

//    public void update(Persone persone, int id) {
//        listpersone.stream()
//                .filter(p -> p.getID() == id)
//                .forEach(p -> {
//                    p.setFirstName(persone.getFirstName());
//                    p.setLastName(persone.getLastName());
//                    p.setAge(persone.getAge());
//                });
//    }
}

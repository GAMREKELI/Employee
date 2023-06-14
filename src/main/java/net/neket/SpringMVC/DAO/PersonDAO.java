package net.neket.SpringMVC.DAO;

import net.neket.SpringMVC.entity.Persone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int ID;

    private static String URL = "jdbc:mysql://localhost:3306/javaTest";

    private static String USERNAME = "pdursley";

    private static String PASSWORD = "Q1234qwe";

    private static Connection connection;

    static {
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
        List<Persone> people = new ArrayList<Persone>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
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

            return people;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPersone(Persone persone) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(10, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, persone.getFirstName());
            preparedStatement.setString(2, persone.getLastName());
            preparedStatement.setInt(3, persone.getAge());
            preparedStatement.setString(4, persone.getDepartament());
            preparedStatement.setString(5, persone.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Persone getPersone(int id) {
        try {
            Persone persone = new Persone();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id = ?");
            preparedStatement.setInt(1, id);

//            System.out.println("id");

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println("id");

            persone.setID(id);
            persone.setFirstName(resultSet.getString("firstName"));
            persone.setLastName(resultSet.getString("lastName"));
            persone.setAge(resultSet.getInt("age"));
            persone.setDepartament(resultSet.getString("departament"));
            persone.setEmail(resultSet.getString("email"));

            return persone;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePersone(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id = ? LIMIT 1");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Persone persone, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET firstName = ?, lastName = ?, age = ?, departament = ?, email = ? WHERE id = ? LIMIT 1");

            preparedStatement.setString(1, persone.getFirstName());
            preparedStatement.setString(2, persone.getLastName());
            preparedStatement.setInt(3, persone.getAge());
            preparedStatement.setString(4, persone.getDepartament());
            preparedStatement.setString(5, persone.getEmail());
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

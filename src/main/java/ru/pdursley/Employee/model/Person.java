package ru.pdursley.Employee.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person {
    private int ID;

    @NotNull
    @Size(min = 1, max = 254, message = "Неверное имя пользователя!")
    private String login;

    @NotNull
    @Size(min = 8, max = 254, message = "Неверный пароль!")
    private String password;

    public Person() {

    }

    public Person(int ID, String login, String password) {
        this.ID = ID;
        this.login = login;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

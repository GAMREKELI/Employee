package ru.pdursley.Employee.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Person {
    @NotNull(message = "Вы не указали имя")
    @Size(min = 2, max = 35, message = "Неккоректное имя")
    private String firstName;
    @NotNull(message = "Вы не указали фамилию")
    @Size(min = 2, max = 35, message = "Неккоректная фамилия")
    private String lastName;

    @NotNull(message = "Вы не указали возраст")
    @Min(value = 0, message = "Неверный возраст")
    private int age;
    private int ID;

    @NotNull(message = "Вы не указали депортамент")
    private String departament;
    @NotNull(message = "Вы не указали почту")
    @Email(message = "Неверная почта")
    private String email;

    public Person() {

    }

    public Person(String firstName, String lastName, int age, int ID, String departament, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.ID = ID;
        this.departament = departament;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getID() {
        return ID;
    }

    public String getDepartament() {
        return departament;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

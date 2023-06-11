package net.neket.SpringMVC.entity;

public class Persone {
    private String firstName;
    private String lastName;
    private int age;
    private int ID;

    private String departament;
    private String email;

    public Persone() {

    }

    public Persone(String firstName, String lastName, int age, int ID, String departament, String email) {
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

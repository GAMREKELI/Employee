package ru.pdursley.Employee.entity;

import ru.pdursley.Employee.model.Person;
import ru.pdursley.Employee.model.Role;

public class Permission {
    private Person person;
    private PersonInfo personInfo;
    private Role role;
    public Permission() {
    }

    public Permission(Person person, PersonInfo personInfo, Role role) {
        this.person = person;
        this.personInfo = personInfo;
        this.role = role;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public Role getRole() {
        return role;
    }
}

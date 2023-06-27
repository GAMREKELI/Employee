package ru.pdursley.Employee.model;

import jakarta.validation.constraints.NotNull;

public class Role {

    @NotNull
    private String role;

    private int id;

    public Role() {

    }

    public Role(String role, int id) {
        this.role = role;
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }
}

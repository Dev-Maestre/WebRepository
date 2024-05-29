package com.example.dsystemserver.Models;

public class User {

    private String username;
    private String password;
    private String email;
    private String industry;
    private String description;
    private String role;
    private int id;

    public User(String username,String email, String password,String role, int id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public User (String email, String password, String name, int id) {
        this.email = email;
        this.password = password;
        this.username = name;
        this.id = id;
    }

    public User (String email, String password, String name, String industry, String description, int id) {
        this.email = email;
        this.password = password;
        this.username = name;
        this.id = id;
        this.industry = industry;
        this.description = description;
    }

    public User (String email, String password, String name, String industry, String description, String role, int id) {
        this.email = email;
        this.password = password;
        this.username = name;
        this.industry = industry;
        this.description = description;
        this.role = role;
        this.id = id;
    }

    public User(String name, String email, String password) {
        this.username = name;
        this.email = email;
        this.password = password;
        //this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getIndustry() {return industry;}

    public String getDescription() {return description;}

    public String getName() {
        return username;
    }

    public int getID() {
        return id;
    }
}

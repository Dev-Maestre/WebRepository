package com.example.dsystem.Models;

public class UserModel {
    private String name;
    private String email;
    private String password;
    private String branch;
    private String description;

    public UserModel(String name, String email, String password, String branch, String description) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.branch = branch;
        this.description = description;
    }
}

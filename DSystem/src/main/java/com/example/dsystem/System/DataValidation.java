package com.example.dsystem.System;

public class DataValidation {
    public static boolean loginValidation(String email, String password) throws Exception {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new Exception("Invalid Data");
        }
        if (!isEmailValid(email)) {
            throw new Exception("invalid Email");
        }
        if (!isPasswordValid(password)) {
            throw new Exception("invalid Password ");
        }
        return true;
    }

    public static boolean registerRecruiterValidation(String name, String email, String password, String industry, String desc) throws Exception {
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty() || industry == null || industry.isEmpty() || desc == null || desc.isEmpty()) {
            throw new Exception("Invalid Data");
        }
        if (!isEmailValid(email)) {
            throw new Exception("invalid Email");
        }
        if (!isPasswordValid(password)) {
            throw new Exception("invalid Password ");
        }
        return true;
    }

    public static boolean registerCandidateValidation(String name, String email, String password) throws Exception {
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new Exception("Invalid Data");
        }
        if (!isEmailValid(email)) {
            throw new Exception("invalid Email");
        }
        if (!isPasswordValid(password)) {
            throw new Exception("invalid Password ");
        }
        return true;
    }

    public static boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
    public static boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).*$");
    }

    public static boolean userValidation(String name, String email, String password) throws Exception {
       if (name == null || name.isEmpty()) {
            throw new Exception("Name is needed");
        }
        if (email == null || email.isEmpty()) {
            throw new Exception("Email is needed");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("Password is needed");
        }
        if (!isEmailValid(email)) {
            throw new Exception("invalid Email");
        }
        if (!isPasswordValid(password)) {
            throw new Exception("invalid Password ");
        }
        return true;
    }


}

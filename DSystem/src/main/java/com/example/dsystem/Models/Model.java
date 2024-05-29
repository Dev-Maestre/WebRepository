package com.example.dsystem.Models;

import com.example.dsystem.Views.ViewFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Model {
    private static Model model = null;
    private final ViewFactory viewFactory;
    private final Connection connection;
    private final ObjectMapper objectMapper;



    private Model() {
        this.viewFactory = new ViewFactory();
        this.connection = new Connection();
        this.objectMapper = new ObjectMapper();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public Connection getConnection() {
        return connection;
    }

}

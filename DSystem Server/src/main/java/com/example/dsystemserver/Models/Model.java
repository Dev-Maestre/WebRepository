package com.example.dsystemserver.Models;

import com.example.dsystemserver.Views.ViewFactory;

import java.sql.SQLException;


public class Model {
    public static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private final SessionManager sessionManager;

    private Model() throws SQLException {
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.sessionManager = new SessionManager();
    }

    public static synchronized Model getInstance() throws SQLException {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public synchronized DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

}

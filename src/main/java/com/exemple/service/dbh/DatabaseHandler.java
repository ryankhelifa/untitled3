package com.exemple.service.dbh;

import java.sql.*;

public class DatabaseHandler {
    private Connection connection;
    public DatabaseHandler(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnection() {
        return connection;
    }


}

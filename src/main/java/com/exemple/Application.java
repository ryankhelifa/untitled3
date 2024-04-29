package com.exemple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.exemple.service.dbh.DatabaseHandler;
import com.exemple.service.service.UserService;
import com.exemple.service.service.MovieService;
import com.exemple.service.service.TicketService;



public class Application {

    // Database connection properties
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/cinema";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "0506";


    public static void main(String[] args) {
        // Initialize database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to database.");



            DatabaseHandler databaseHandler = new DatabaseHandler(connection);
            UserService userService = new UserService(databaseHandler);
            MovieService movieService = new MovieService(databaseHandler);
            TicketService ticketService = new TicketService(databaseHandler);




        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }




    }

}

package com.exemple.service.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;

import com.exemple.model.User;
import com.exemple.service.dbh.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebService (serviceName = "UserService")
@WebServlet (urlPatterns = "/UserService")
public class UserService {

    private Connection connection;

    public UserService(DatabaseHandler databaseHandler) {
        this.connection = databaseHandler.getConnection();
    }

    // SOAP operation for user login
    @WebMethod(operationName = "login")
    public User login(@WebParam(name = "email") String email,
                      @WebParam(name = "password") String password) {
        // Validate input
        if (email == null || password == null ||
                email.isEmpty() || password.isEmpty()) {
            return null;
        }

        // Check if user exists in the database
        User user = findUserByEmail(Long.parseLong(email));
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    // Find user by email
    public User findUserByEmail(Long userId) {
        // Implement database query to find user by email
        User user = null;
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("userId"),
                        resultSet.getString("name"),
                        resultSet.getString("familyName"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("isAdmin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }
    // SOAP operation for user registration

    @WebMethod(operationName = "register")
    public boolean register(@WebParam(name = "name") String name,
                            @WebParam(name = "familyName") String familyName,
                            @WebParam(name = "email") String email,
                            @WebParam(name = "password") String password) {
        // Validate input
        if (name == null || familyName == null || email == null || password == null ||
                name.isEmpty() || familyName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        // Check if user already exists in the database
        if (findUserByEmail(Long.parseLong(email)) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        // Insert new user into the database
        String query = "INSERT INTO users (name, familyName, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, familyName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.executeUpdate();
            login(email, password); // Automatically login after registration

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true; // Return true if registration is successful
    }
}
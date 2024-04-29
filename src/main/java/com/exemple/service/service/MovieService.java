package com.exemple.service.service;

import com.exemple.model.Movie;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.exemple.service.dbh.DatabaseHandler;



@WebService(serviceName = "MovieService")
@WebServlet(urlPatterns = "/MovieService")
public class MovieService {

    private DatabaseHandler databaseHandler;

    public MovieService(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    // SOAP operation for fetching movies


    @WebMethod (operationName = "fetchMovies")
    public List<Movie> fetchMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";
        try (PreparedStatement statement = databaseHandler.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie(
                        resultSet.getLong("movieId"),
                        resultSet.getString("title"),
                        resultSet.getDate("releaseDate")
                );
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();


        }

        return movies;
    }
    @WebMethod (operationName = "fetchMovie")
    public Movie fetchMovie(@WebParam(name = "title") String title) {
        Movie movie = null;
        String query = "SELECT * FROM movies WHERE title like ?";
        try (PreparedStatement statement = databaseHandler.getConnection().prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                movie = new Movie(
                        resultSet.getLong("movieId"),
                        resultSet.getString("title"),
                        resultSet.getDate("releaseDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

}

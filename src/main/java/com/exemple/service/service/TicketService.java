package com.exemple.service.service;

import com.exemple.model.Diffusion;
import com.exemple.model.Movie;
import com.exemple.model.Ticket;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exemple.model.User;
import com.exemple.service.dbh.DatabaseHandler;


@WebService (serviceName = "TicketService")
@WebServlet (urlPatterns = "/TicketService")
public class TicketService {

    private DatabaseHandler databaseHandler;

    public TicketService(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    // SOAP operation for fetching tickets

    public List<Ticket> fetchTickets(Long userId) {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets WHERE email = ?";
        try (PreparedStatement statement = databaseHandler.getConnection().prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket(
                        resultSet.getLong("ticketId"),
                        resultSet.getLong("userId"),
                        resultSet.getLong("diffusionId")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
    public Diffusion fetchDiffusions(Ticket ticket) {


        String query = "SELECT * FROM diffusions WHERE diffusionId = ?";
        Diffusion diffusion = null;
        try (PreparedStatement statement = databaseHandler.getConnection().prepareStatement(query)) {
            statement.setLong(1, ticket.getDiffusionId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                diffusion = new Diffusion(
                        resultSet.getLong("diffusionId"),
                        resultSet.getLong("movieId"),
                        resultSet.getDate("date"),
                        resultSet.getString("time")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diffusion;
    }
    public Movie fetchMovie (Diffusion diffusions) {
        Movie movie = null;
        String query = "SELECT * FROM movies WHERE movieId = ?";
        try (PreparedStatement statement = databaseHandler.getConnection().prepareStatement(query)) {
            statement.setLong(1, diffusions.getMovieId());
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
    @WebMethod(operationName = "clienTickets")
    public List<Ticket> clienTickets (@WebParam(name = "email") String email,
                                      @WebParam(name = "password") String password) {
        if (email == null || password == null ||
                email.isEmpty() || password.isEmpty()) {
            return null;
        }
        UserService Login = new UserService(databaseHandler);
        User user = Login.login(email, password);
        if (user== null) {
            return null;
        }
        List<Ticket> tickets = fetchTickets(user.getUserId());

        // for each ticket, fetch the corresponding diffusion
        for (int i = 0; i < tickets.size(); i++) {
            Diffusion diffusion = fetchDiffusions(tickets.get(i));
            Movie movie = fetchMovie(diffusion);
            tickets.get(i).setDiffusion(diffusion);
            tickets.get(i).getDiffusion().setMovie(movie);
        }
        return tickets;
    }



    @WebMethod(operationName = "buyTicket")
    public void buyTicket(@WebParam(name = "email") String email,
                          @WebParam(name = "password") String password ,
                          @WebParam(name = "diffusionId") Long diffusionId) {
        UserService Login = new UserService(databaseHandler);
        User user = Login.login(email, password);
        if (user == null) {
            return;
        }
        String query = "INSERT INTO tickets (userId, diffusionId) VALUES (?, ?)";
        try (PreparedStatement statement = databaseHandler.getConnection().prepareStatement(query)) {
                statement.setLong(1, user.getUserId());
            statement.setLong(2, diffusionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

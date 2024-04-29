package com.exemple.model;

import java.util.Date;
import com.exemple.model.Movie;

public class Diffusion {

    private Long DiffusionId;

    private Long movieId;
    private Movie movie;

    private Date date;
    private String time;


    public Diffusion() {
    }

    public Diffusion(Long DiffusionId, Long movieId, Date date, String time) {
        this.DiffusionId = DiffusionId;
        this.movieId = movieId;
        this.date = date;
        this.time = time;
    }

        public Diffusion(Long DiffusionId, Movie movie, Date date, String time) {
        this.DiffusionId = DiffusionId;
        this.movie = movie;
        this.date = date;
        this.time = time;
    }

public Long getDiffusionId() {
        return DiffusionId;
    }

    public void setDiffusionId(Long DiffusionId) {
        this.DiffusionId = DiffusionId;
    }

    public Movie getMovie() {
        return movie;
    }

        public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }


    public void setTime(int  time) {
        switch(time){
            case 1:
                this.time = "10:00";
                break;
            case 2:
                this.time = "14:00";
                break;
            case 3:
                this.time = "18:00";
                break;
            case 4:
                this.time = "22:00";
                break;
            default:
                throw new IllegalArgumentException("Invalid time. Please enter a valid time.");
        }
    }
    public Long getMovieId() {
        return movieId;
    }
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }


    @Override
    public String toString() {
        return "Diffusion{" +
                "DiffusionId=" + DiffusionId +
                "," + movie.toString() + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}


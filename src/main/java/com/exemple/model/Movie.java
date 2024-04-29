package com.exemple.model;

import java.util.Date;

public class Movie {
    private Long movieId;
    private String title;
    private Date releaseDate;

    public Movie() {
    }

    public Movie(Long movieId, String title, Date releaseDate) {
        this.movieId = movieId;
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
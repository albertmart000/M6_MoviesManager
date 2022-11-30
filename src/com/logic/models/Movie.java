package com.logic.models;

import java.io.Serializable;
import java.util.Objects;

public class Movie implements Serializable, Comparable<Movie> {

    private String title;
    private Genre genre;
    private Director director;
    private Double rating = Double.valueOf(0);
    private int year;
    private boolean ifWatched;
    private PEGI pegi;

    public Movie() {
    }

    public Movie(String title, Genre genre, Director director, int year, boolean ifWatched, PEGI pegi) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.ifWatched = ifWatched;
        this.pegi = pegi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isIfWatched() {
        return ifWatched;
    }

    public void setIfWatched(boolean ifWatched) {
        this.ifWatched = ifWatched;
    }

    public PEGI getPegi() {
        return pegi;
    }

    public void setPegi(PEGI pegi) {
        this.pegi = pegi;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.director);
        hash = 83 * hash + this.year;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Objects.equals(this.director, other.director);
    }

    @Override
    public String toString() {
        return "Movie{" + "title=" + title + ", genre=" + genre + ", director=" + director + ", rating=" + String.format("%.2f", rating) + ", year=" + year + ", ifWatched=" + ifWatched + ", pegi=" + pegi + '}';
    }

    @Override
    public int compareTo(Movie o) {
        if (this.title.compareToIgnoreCase(o.title) != 0) {
            return this.title.compareToIgnoreCase(o.title);
        } return this.getYear() - o.getYear();
    }

}

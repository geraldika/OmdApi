package com.example.omdapi.main.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Film {

    @SerializedName("imdbID")
    @Expose
    private String imdbID;

    @SerializedName("Title") /*title of the film*/
    @Expose
    private String title;

    @SerializedName("Ratings")
    @Expose
    private List<String> ratings;

    @SerializedName("Country")
    @Expose
    private String country;

    @SerializedName("Director")
    @Expose
    private String director;

    @SerializedName("Actors")
    @Expose
    private String actors;

    @SerializedName("Plot")
    @Expose
    private String plot;

    @SerializedName("Poster")
    @Expose
    private String poster;

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getRatings() {
        return ratings;
    }

    public void setRatings(List<String> ratings) {
        this.ratings = ratings;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title) &&
                Objects.equals(ratings, film.ratings) &&
                Objects.equals(country, film.country) &&
                Objects.equals(director, film.director) &&
                Objects.equals(actors, film.actors) &&
                Objects.equals(plot, film.plot) &&
                Objects.equals(poster, film.poster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, ratings, country, director, actors, plot, poster);
    }

    /* Постер фильма
 Название!
 Рейтинг!
 Страна!
 Режиссер!
 Список актеров!
 Описание фильма*/


}

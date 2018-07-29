package com.example.omdbapi.api.wrapper;

import com.example.omdbapi.main.model.Film;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchWrapper {

    @SerializedName("Search")
    @Expose
    private List<Film> films;

    public SearchWrapper() {

    }

    public SearchWrapper(List<Film> filmList) {
        this.films = filmList;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}

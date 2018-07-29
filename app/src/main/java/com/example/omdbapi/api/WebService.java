package com.example.omdbapi.api;

import android.support.annotation.NonNull;

import com.example.omdbapi.api.wrapper.SearchWrapper;
import com.example.omdbapi.main.model.Film;

import io.reactivex.Observable;
import retrofit2.Call;

public class WebService {

    private static final String OMD_API_KEY = "484cf683";
    private OmdbApi omdbApi;

    public WebService(OmdbApi omdApi) {
        this.omdbApi = omdApi;
    }

    public Observable<SearchWrapper> getFilms(@NonNull String searchString) {
        return omdbApi.getFilms(OMD_API_KEY, searchString);
    }

    public Call<Film> getFilmInfo(@NonNull String idFilm) {
        return omdbApi.getFilmInfo(OMD_API_KEY, idFilm);
    }
}

package com.example.omdapi.api;

import com.example.omdapi.main.model.Film;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OmdbApi {

    @GET(".")
    Observable<List<Film>> getFilms(@Query("apikey") String apiKey, @Query("t") String title);
}

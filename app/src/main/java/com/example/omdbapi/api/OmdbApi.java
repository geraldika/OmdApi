package com.example.omdbapi.api;

import com.example.omdbapi.api.wrapper.SearchWrapper;
import com.example.omdbapi.main.model.Film;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.omdbapi.api.Api.API_GET_FILM_BY_ID;
import static com.example.omdbapi.api.Api.API_KEY;
import static com.example.omdbapi.api.Api.API_SEARCH_FILMS;

public interface OmdbApi {

    @GET(".")
    Observable<SearchWrapper> getFilms(@Query(API_KEY) String apiKey, @Query(API_SEARCH_FILMS) String searchString);

    @GET(".")
    Call<Film> getFilmInfo(@Query(API_KEY) String apiKey, @Query(API_GET_FILM_BY_ID) String idFilm);

}

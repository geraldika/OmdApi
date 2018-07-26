package com.example.omdapi.api;

import com.example.omdapi.api.wrapper.SearchWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApi {

    @GET(".")
    Observable<SearchWrapper> getFilms(@Query("apikey") String apiKey, @Query("s") String searchString);
}

package com.example.omdapi.api;

import com.example.omdapi.main.model.Film;

import java.util.List;

import rx.Observable;

import static com.example.omdapi.utils.Constants.OMD_API_KEY;

public class WebService {

    private OmdbApi omdApi;

    public WebService(OmdbApi omdApi) {
        this.omdApi = omdApi;
    }

    public Observable<List<Film>> getFilms() {
        return omdApi.getFilms(OMD_API_KEY, "batman");
    }
}

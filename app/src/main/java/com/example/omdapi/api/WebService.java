package com.example.omdapi.api;

import android.support.annotation.NonNull;

import com.example.omdapi.api.wrapper.SearchWrapper;
import com.example.omdapi.main.model.Film;

import java.util.List;

import io.reactivex.Observable;

import static com.example.omdapi.utils.Constants.OMD_API_KEY;

public class WebService {

    private OmdbApi omdApi;

    public WebService(OmdbApi omdApi) {
        this.omdApi = omdApi;
    }

    public Observable<SearchWrapper> getFilms(@NonNull String searchString) {
        return omdApi.getFilms(OMD_API_KEY, searchString);
    }
}

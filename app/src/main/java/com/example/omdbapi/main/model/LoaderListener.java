package com.example.omdbapi.main.model;

import android.support.annotation.NonNull;

import java.util.List;

public interface LoaderListener {

    void onError();

    void onLoading();

    void onFinished();

    void onDataLoaded(String posterUrl, String title, @NonNull List<Rating> rating, String country,
                      String director, String actors, String plot);

}

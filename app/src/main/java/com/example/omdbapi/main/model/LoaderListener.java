package com.example.omdbapi.main.model;

public interface LoaderListener {

    void onError();

    void onLoading();

    void onFinished();

    void onDataLoaded(String posterUrl, String title, String rating, String country,
                      String director, String actors, String plot);

}

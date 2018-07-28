package com.example.omdbapi.main.view;

import android.support.annotation.NonNull;

import com.example.omdbapi.base.BaseView;
import com.example.omdbapi.main.model.Rating;

import java.util.List;

public interface FilmInfoView extends BaseView {

    void showFilmInfo(String posterUrl, String title, @NonNull List<Rating> rating, String country,
                      String director, String actors, String plot);
}

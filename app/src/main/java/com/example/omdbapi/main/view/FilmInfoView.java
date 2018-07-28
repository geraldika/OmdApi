package com.example.omdbapi.main.view;

import com.example.omdbapi.base.BaseView;

public interface FilmInfoView extends BaseView {

    void showFilmInfo(String posterUrl, String title, String rating, String country,
                      String director, String actors, String plot);
}

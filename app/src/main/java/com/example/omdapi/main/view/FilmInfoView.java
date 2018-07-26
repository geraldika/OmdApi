package com.example.omdapi.main.view;

import com.example.omdapi.base.BaseView;

public interface FilmInfoView extends BaseView {

    void showFilmInfo(String posterUrl, String title, String rating, String country,
                      String director, String actors, String plot);
}

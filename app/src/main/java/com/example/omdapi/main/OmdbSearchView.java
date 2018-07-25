package com.example.omdapi.main;

import com.example.omdapi.base.BaseView;
import com.example.omdapi.main.model.Film;

import java.util.List;

public interface OmdbSearchView extends BaseView {
    void showFilms(List<Film> films);
}

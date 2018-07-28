package com.example.omdbapi.main.view;

import com.example.omdbapi.base.BaseView;
import com.example.omdbapi.main.model.Film;

import java.util.List;

public interface SearchFilmsView extends BaseView {
    void showFilms(List<Film> films);

    void clearData();
}

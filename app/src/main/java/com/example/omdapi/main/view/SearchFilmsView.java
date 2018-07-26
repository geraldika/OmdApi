package com.example.omdapi.main.view;

import com.example.omdapi.base.BaseView;
import com.example.omdapi.main.model.Film;

import java.util.List;

public interface SearchFilmsView extends BaseView {
    void showFilms(List<Film> films);
}

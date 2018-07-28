package com.example.omdbapi.main.model;

import android.support.annotation.NonNull;

public interface FilmInfoInteractorImpl {

    void getFilmInfo(@NonNull LoaderListener loaderListener, String idFilm);

}

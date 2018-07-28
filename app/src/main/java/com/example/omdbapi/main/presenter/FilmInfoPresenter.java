package com.example.omdbapi.main.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.omdbapi.main.model.FilmInfoInteractor;
import com.example.omdbapi.main.model.LoaderListener;
import com.example.omdbapi.main.model.Rating;
import com.example.omdbapi.main.view.FilmInfoView;

import java.util.List;

import static com.example.omdbapi.utils.Constants.EMPTY;

@InjectViewState
public class FilmInfoPresenter extends MvpPresenter<FilmInfoView> implements LoaderListener {

    private static final String TAG = FilmInfoPresenter.class.getSimpleName();

    private FilmInfoInteractor filmInfoInteractor;

    public FilmInfoPresenter() {
        filmInfoInteractor = new FilmInfoInteractor();
    }

    public void getFilmInfo(String idFilm) {

        if (idFilm != null && !EMPTY.equals(idFilm)) {
            filmInfoInteractor.getFilmInfo(this, idFilm);
        } else {
            getViewState().showError();
        }
    }

    @Override
    public void onError() {
        getViewState().showError();
    }

    @Override
    public void onLoading() {
        getViewState().showLoading();
    }

    @Override
    public void onFinished() {
        getViewState().hideLoading();
    }

    @Override
    public void onDataLoaded(String posterUrl, String title, @NonNull List<Rating> rating, String country, String director, String actors, String plot) {
        getViewState().showFilmInfo(posterUrl, title, rating, country, director, actors, plot);
    }
}

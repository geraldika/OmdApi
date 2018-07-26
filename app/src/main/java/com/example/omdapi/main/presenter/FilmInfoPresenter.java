package com.example.omdapi.main.presenter;

import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.omdapi.R;
import com.example.omdapi.api.WebService;
import com.example.omdapi.app.App;
import com.example.omdapi.main.model.Film;
import com.example.omdapi.main.view.FilmInfoView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.omdapi.utils.Constants.EMPTY;

@InjectViewState
public class FilmInfoPresenter extends MvpPresenter<FilmInfoView> {

    private static final String TAG = FilmInfoPresenter.class.getSimpleName();
    @Inject
    WebService webService;
    private Context context;

    public FilmInfoPresenter() {
        App.getAppComponent().inject(this);
        context = App.getAppComponent().getContext();
    }

    public void getFilmInfo(String idFilm) {
        Log.d(TAG, " film id: " + idFilm);
        if (idFilm != null && !EMPTY.equals(idFilm)) {

            getViewState().showLoading();
            webService.getFilmInfo(idFilm).enqueue(new Callback<Film>() {
                @Override
                public void onResponse(Call<Film> call, Response<Film> response) {
                    if (response.isSuccessful()) {
                        Film film = response.body();
                        if (film != null) {

                            String posterUrl = film.getActors() != null ? film.getPoster() : EMPTY;

                            String title = film.getTitle() != null && !EMPTY.equals(film.getTitle()) ?
                                    film.getTitle() : context.getString(R.string.not_found);
                            //todo
                            String rating = film.getRatings() != null && !EMPTY.equals(film.getTitle()) ?
                                    film.getTitle() : context.getString(R.string.not_found);

                            String country = film.getTitle() != null && !EMPTY.equals(film.getCountry()) ?
                                    film.getCountry() : context.getString(R.string.not_found);

                            String director = film.getTitle() != null && !EMPTY.equals(film.getTitle()) ?
                                    film.getDirector() : context.getString(R.string.not_found);

                            String actors = film.getActors() != null && EMPTY.equals(film.getTitle()) ?
                                    film.getActors() : context.getString(R.string.not_found);

                            String plot = film.getActors() != null && !EMPTY.equals(film.getTitle()) ?
                                    film.getPlot() : context.getString(R.string.not_found);

                            getViewState().showFilmInfo(posterUrl, title, rating, country, director, actors, plot);

                        }
                    } else getViewState().showError();

                    getViewState().showError();
                }

                @Override
                public void onFailure(Call<Film> call, Throwable t) {
                    Log.d(TAG, " onFailure: " + t.toString());
                    getViewState().showError();
                }
            });
        } else {
            getViewState().showError();
        }
    }
}

package com.example.omdbapi.main.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.omdbapi.R;
import com.example.omdbapi.api.WebService;
import com.example.omdbapi.app.App;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.omdbapi.utils.Constants.EMPTY;

public class FilmInfoInteractor implements FilmInfoInteractorImpl {

    private static final String TAG = FilmInfoInteractor.class.getSimpleName();

    @Inject
    WebService webService;
    private Context context;

    public FilmInfoInteractor() {
        App.getAppComponent().inject(this);
        context = App.getAppComponent().getContext();
    }

    @Override
    public void getFilmInfo(@NonNull LoaderListener loaderListener, String idFilm) {
        loaderListener.onLoading();
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

                        loaderListener.onDataLoaded(posterUrl, title, rating, country, director, actors, plot);

                    }
                } else loaderListener.onError();

                loaderListener.onFinished();
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Log.d(TAG, " onFailure: " + t.toString());
                loaderListener.onError();
                loaderListener.onFinished();
            }
        });
    }
}

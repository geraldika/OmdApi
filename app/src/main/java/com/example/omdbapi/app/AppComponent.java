package com.example.omdbapi.app;

import android.content.Context;

import com.example.omdbapi.api.module.ApiModule;
import com.example.omdbapi.api.module.ServiceModule;
import com.example.omdbapi.main.model.FilmInfoInteractor;
import com.example.omdbapi.main.presenter.FilmInfoPresenter;
import com.example.omdbapi.main.presenter.SearchFilmsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, ApiModule.class, ServiceModule.class})
public interface AppComponent {

    Context getContext();

    void inject(App app);

    void inject(SearchFilmsPresenter searchFilmsPresenter);

    void inject(FilmInfoInteractor filmInfoInteractor);

}

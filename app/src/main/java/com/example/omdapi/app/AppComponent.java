package com.example.omdapi.app;

import android.content.Context;

import com.example.omdapi.api.ApiModule;
import com.example.omdapi.api.ServiceModule;
import com.example.omdapi.main.presenter.SearchFilmsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, ApiModule.class, ServiceModule.class})
public interface AppComponent {

    Context getContext();

    void inject(App app);

    void inject(SearchFilmsPresenter omdSearchPresenter);

}

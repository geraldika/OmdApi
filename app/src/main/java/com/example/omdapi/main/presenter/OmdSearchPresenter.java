package com.example.omdapi.main.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.omdapi.api.WebService;
import com.example.omdapi.app.App;
import com.example.omdapi.main.OmdbSearchView;
import com.example.omdapi.main.model.Film;
import com.example.omdapi.main.model.OmdSearchInteractor;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class OmdSearchPresenter extends MvpPresenter<OmdbSearchView> {

    private static final String TAG = OmdSearchPresenter.class.getSimpleName();

    @Inject
    WebService webService;

    private Film film;

    private OmdSearchInteractor searchFilmsInteractor;

    public OmdSearchPresenter() {
        App.getAppComponent().inject(this);
        loadData();
        searchFilmsInteractor = new OmdSearchInteractor();
    }

    //todo
    public void onItemClick() {
        // getViewState().
    }

    //todo in interceptor
    private void loadData() {

        webService.getFilms()
                .doOnSubscribe(getViewState()::showLoading)
                .doOnTerminate(getViewState()::hideLoading)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showFilms, throwable -> getViewState().showError());
    }

}

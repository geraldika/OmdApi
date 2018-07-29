package com.example.omdbapi.main.presenter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.SearchView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.omdbapi.api.WebService;
import com.example.omdbapi.api.wrapper.SearchWrapper;
import com.example.omdbapi.app.App;
import com.example.omdbapi.main.view.SearchFilmsView;
import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SearchFilmsPresenter extends MvpPresenter<SearchFilmsView> {

    private static final String TAG = SearchFilmsPresenter.class.getSimpleName();
    @Inject
    WebService webService;
    private Disposable disposable; //to unsubscribe

    public SearchFilmsPresenter() {
        App.getAppComponent().inject(this);
    }

    public void init(SearchView searchView) {
        int timeInterval = 100;

        disposable = RxSearchView.queryTextChanges(searchView)
                .filter(charSequence -> !isSearchStringEmpty(charSequence))
                .throttleLast(timeInterval, TimeUnit.MILLISECONDS)
                .debounce(timeInterval, TimeUnit.MILLISECONDS)
                .flatMap(this::getSearchedTask)

                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())

                .subscribe(this::showFilms,
                        throwable -> {
                            getViewState().showError();
                            getViewState().hideLoading();
                            Log.d(TAG, " err: " + throwable.toString());
                        });
    }

    private boolean isSearchStringEmpty(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence) && charSequence.length() <= 1) {
            getViewState().clearData();
            return true;
        } else {
            getViewState().showLoading();
            return false;
        }
    }

    private void showFilms(SearchWrapper searchWrapper) {
        if (searchWrapper != null && searchWrapper.getFilms() != null)
            getViewState().showFilms(searchWrapper.getFilms());
        else getViewState().showFilms(Collections.emptyList());

        getViewState().hideLoading();
    }

    private Observable<SearchWrapper> getSearchedTask(CharSequence s) {
        return webService.getFilms(s.toString())
                .subscribeOn(Schedulers.io()) // network
                .observeOn(AndroidSchedulers.mainThread())
                //for test " AND 1=1 -> 403 Forbidden
                .onErrorReturnItem(new SearchWrapper(Collections.emptyList()));
    }

    public void disposeObservable() {
        if (!isDisposed())
            disposable.dispose();
    }

    private boolean isDisposed() {
        return disposable.isDisposed();
    }
}

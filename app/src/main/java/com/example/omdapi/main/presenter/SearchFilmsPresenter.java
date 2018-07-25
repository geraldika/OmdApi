package com.example.omdapi.main.presenter;

import android.util.Log;
import android.widget.EditText;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.omdapi.api.WebService;
import com.example.omdapi.app.App;
import com.example.omdapi.main.OmdbSearchView;
import com.example.omdapi.main.model.Film;
import com.example.omdapi.main.model.OmdSearchInteractor;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


@InjectViewState
public class SearchFilmsPresenter extends MvpPresenter<OmdbSearchView> {

    private static final String TAG = SearchFilmsPresenter.class.getSimpleName();

    @Inject
    WebService webService;

    private Disposable disposable;
    private List<Film> films;

    private OmdSearchInteractor searchFilmsInteractor;

    public SearchFilmsPresenter() {
        App.getAppComponent().inject(this);
        searchFilmsInteractor = new OmdSearchInteractor();
    }

    public void init(EditText editText) {

        DisposableObserver<TextViewTextChangeEvent> observer = new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                films = getSearchedTask(onTextChangeEvent.text().toString());
                getViewState().showFilms(films);
            }
        };

        disposable = //Disposable
                RxTextView.textChangeEvents(editText)
                        .debounce(400, TimeUnit.MILLISECONDS) // default Scheduler is Computation
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer);
    }

    private List<Film> getSearchedTask(String s) {
        return films;
    }

    //todo
    public void onItemClick() {
        // getViewState().
    }

    //todo in interceptor
    private void loadData() {

       /* webService.getFilms()
                .doOnSubscribe(getViewState()::showLoading)
                .doOnTerminate(getViewState()::hideLoading)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::showFilms, throwable -> getViewState().showError());*/
    }
}

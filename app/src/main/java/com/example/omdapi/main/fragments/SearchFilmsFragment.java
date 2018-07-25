package com.example.omdapi.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omdapi.R;
import com.example.omdapi.main.OmdbSearchView;
import com.example.omdapi.main.model.Film;
import com.example.omdapi.main.presenter.SearchFilmsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchFilmsFragment extends MvpAppCompatFragment implements OmdbSearchView {

    @BindView(R.id.search_et)
    EditText searchEt;

    @InjectPresenter
    SearchFilmsPresenter searchFilmsPresenter;

    private Unbinder unbinder;

    public static Fragment newInstance() {
        return new SearchFilmsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_films, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchFilmsPresenter.init(searchEt);
    }


    @Override
    public void showFilms(List<Film> films) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

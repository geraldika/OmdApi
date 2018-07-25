package com.example.omdapi.main;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omdapi.R;
import com.example.omdapi.base.BaseActivity;
import com.example.omdapi.main.model.Film;
import com.example.omdapi.main.presenter.OmdSearchPresenter;

import java.util.List;

public class OmdbActivity extends BaseActivity implements OmdbSearchView {

    @InjectPresenter
    OmdSearchPresenter omdSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void showFilms(List<Film> films) {

    }
}

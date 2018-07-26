package com.example.omdapi.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.omdapi.R;
import com.example.omdapi.main.fragments.FilmInfoFragment;
import com.example.omdapi.main.fragments.SearchFilmsFragment;

public class OmdbActivity extends AppCompatActivity implements SearchFilmsFragment.OnShowFilmInfoListener {

    private static final String SEARCH_FRAGMENT = "search_fragment";
    private static final String INFO_FRAGMENT = "info_fragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omdb);

        initSearchFragment();
    }


    private void initSearchFragment() {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SEARCH_FRAGMENT);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_layout, SearchFilmsFragment.newInstance(), INFO_FRAGMENT)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .attach(fragment)
                    .commit();
        }
    }

    private void initInfoFragment(@NonNull String idFilm) {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(INFO_FRAGMENT);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_layout, FilmInfoFragment.newInstance(idFilm), SEARCH_FRAGMENT)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .attach(fragment)
                    .commit();
        }
    }

    @Override
    public void onShowFilmInfo(@NonNull String idFilm) {
        initInfoFragment(idFilm);
    }
}

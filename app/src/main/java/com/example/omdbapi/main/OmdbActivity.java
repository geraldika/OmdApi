package com.example.omdbapi.main;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.omdbapi.R;
import com.example.omdbapi.main.fragments.FilmInfoFragment;
import com.example.omdbapi.main.fragments.SearchFilmsFragment;

public class OmdbActivity extends MvpAppCompatActivity implements SearchFilmsFragment.OnShowFilmInfoListener {

    private static final String SEARCH_FRAGMENT = "search_fragment";
    private static final String INFO_FRAGMENT = "info_fragment";
    private static final String TAG = OmdbActivity.class.getSimpleName();

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omdb);

        initSearchFragment();
    }

    public void initSearchFragment() {
        fragment = getSupportFragmentManager().findFragmentByTag(SEARCH_FRAGMENT);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_layout, SearchFilmsFragment.newInstance(), SEARCH_FRAGMENT)
                    .addToBackStack(SEARCH_FRAGMENT)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, SEARCH_FRAGMENT)
                    .commit();
        }
    }

    private void initInfoFragment(@NonNull String idFilm) {
        Log.d(TAG, "init FilmInfoFragment, film id: " + idFilm);
        fragment = getSupportFragmentManager().findFragmentByTag(INFO_FRAGMENT);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_layout, FilmInfoFragment.newInstance(idFilm), INFO_FRAGMENT)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, INFO_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void onShowFilmInfo(@NonNull String idFilm) {
        initInfoFragment(idFilm);
    }

    /* current fragment */
    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.container_layout);
    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() instanceof SearchFilmsFragment &&
                getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else if (getCurrentFragment() instanceof SearchFilmsFragment &&
                getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        } else initSearchFragment();
    }

    /* hide keyboard by touch the screen */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //todo
            if (v instanceof RelativeLayout || v instanceof TextView || v instanceof RecyclerView) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    hideKeyboard(v);
                    if (getCurrentFragment() instanceof SearchFilmsFragment && fragment != null) {
                        try {
                            ((SearchFilmsFragment) fragment).setSearchViewTitle();
                        } catch (Exception e) {
                            //todo
                            Log.d(TAG, " null fr: " + e.toString());
                        }
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

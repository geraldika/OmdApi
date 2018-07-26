package com.example.omdapi.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

public class FilmInfoFragment extends MvpAppCompatFragment {

    private static final String ID_FILM = "idFilm";

    public static FilmInfoFragment newInstance(@NonNull String idFilm) {
        Fragment fragment = new FilmInfoFragment();
        Bundle args = new Bundle();
        args.putString(ID_FILM, idFilm);
        fragment.setArguments(args);
        return new FilmInfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

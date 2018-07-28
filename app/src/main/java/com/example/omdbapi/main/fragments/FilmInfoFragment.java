package com.example.omdbapi.main.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.omdbapi.R;
import com.example.omdbapi.main.OmdbActivity;
import com.example.omdbapi.main.presenter.FilmInfoPresenter;
import com.example.omdbapi.main.view.FilmInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.omdbapi.utils.Constants.EMPTY;

public class FilmInfoFragment extends MvpAppCompatFragment implements FilmInfoView {

    private static final String ID_FILM = "idFilm";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.poster_iv)
    ImageView posterIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.rating_tv)
    TextView ratingTv;
    @BindView(R.id.country_tv)
    TextView countryTv;
    @BindView(R.id.director_tv)
    TextView directorTv;
    @BindView(R.id.actors_tv)
    TextView actorsTv;
    @BindView(R.id.film_description_tv)
    TextView filmDescriptionTv;
    ProgressDialog progressDialog;
    @InjectPresenter
    FilmInfoPresenter filmInfoPresenter;
    private Unbinder unbinder;
    private OmdbActivity activity;

    public static FilmInfoFragment newInstance(@NonNull String idFilm) {

        FilmInfoFragment fragment = new FilmInfoFragment();
        Bundle args = new Bundle();
        args.putString(ID_FILM, idFilm);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        activity = (OmdbActivity) getActivity();

        setCancelableProgressDialog();
        initNavigationBtn();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filmInfoPresenter.getFilmInfo(getIdFilmFromBundle());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showFilmInfo(String posterUrl, String title, String rating, String country,
                             String director, String actors, String plot) {
        loadPoster(posterUrl);

        toolbar.setTitle(title);
        titleTv.setText(title);
        ratingTv.setText(rating);
        countryTv.setText(country);
        directorTv.setText(director);
        actorsTv.setText(actors);
        filmDescriptionTv.setText(plot);
    }

    @Override
    public void showError() {
        Toast.makeText(activity, activity.getString(R.string.smth_went_wrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(activity, EMPTY, getString(R.string.loading), true);
    }

    @Override
    public void hideLoading() {
        if (isProgressDialogShowing())
            progressDialog.dismiss();
    }

    private String getIdFilmFromBundle() {
        return getArguments() != null ? getArguments().getString(ID_FILM, EMPTY) : EMPTY;
    }

    private void loadPoster(String url) {
        Glide
                .with(this)
                .load(url)
                .apply(new RequestOptions()
                        .override(300, 300)
                        .placeholder(R.drawable.no_image)
                        .error(R.drawable.no_image))
                .into(posterIv);
    }

    private void initNavigationBtn() {
        if (activity != null)
            activity.setSupportActionBar(toolbar);
        toolbar.setTitle(EMPTY);
        toolbar.setNavigationOnClickListener((View v) -> {
            activity.initSearchFragment();
        });
    }

    private boolean isProgressDialogShowing() {
        return progressDialog != null && progressDialog.isShowing();
    }

    private void setCancelableProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
    }
}

package com.example.omdapi.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omdapi.R;
import com.example.omdapi.main.SearchView;
import com.example.omdapi.main.adapter.FilmsAdapter;
import com.example.omdapi.main.model.Film;
import com.example.omdapi.main.presenter.SearchFilmsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchFilmsFragment extends MvpAppCompatFragment implements SearchView, FilmsAdapter.OnClickItemListener {

    private static final String TAG = SearchFilmsFragment.class.getSimpleName();

    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @InjectPresenter
    SearchFilmsPresenter searchFilmsPresenter;

    private FilmsAdapter adapter;
    private Unbinder unbinder;

    private OnShowFilmInfoListener onShowFilmInfoListener;

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
        initFilmsAdapter();
        searchFilmsPresenter.init(searchEt);
    }


    public void setOnShowFilmInfoListener(OnShowFilmInfoListener onShowFilmInfoListener) {
        this.onShowFilmInfoListener = onShowFilmInfoListener;
    }


    @Override
    public void showFilms(List<Film> films) {
        Log.d(TAG, "show films: " + films.size());
        adapter.setData(films);
    }

    /* FilmsAdapter */
    @Override
    public void onItemClicked(@NonNull String imdbID) {
        onShowFilmInfoListener.onShowFilmInfo(imdbID);
    }

    @Override
    public void showError() {
        Log.d(TAG, "showError");
        adapter.clearData();
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "showLoading");
    }

    @Override
    public void hideLoading() {
        Log.d(TAG, "hideLoading");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void initFilmsAdapter() {
        if (adapter == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new FilmsAdapter();
            recyclerView.setAdapter(adapter);
            adapter.setOnClickItemListener(this);
        }
    }

    public interface OnShowFilmInfoListener {
        void onShowFilmInfo(@NonNull String idFilm);
    }
}

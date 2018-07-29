package com.example.omdbapi.main.fragments;

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
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.omdbapi.R;
import com.example.omdbapi.app.App;
import com.example.omdbapi.main.OmdbActivity;
import com.example.omdbapi.main.adapter.FilmsAdapter;
import com.example.omdbapi.main.model.Film;
import com.example.omdbapi.main.presenter.SearchFilmsPresenter;
import com.example.omdbapi.main.view.SearchFilmsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.omdbapi.utils.Constants.EMPTY;

public class SearchFilmsFragment extends MvpAppCompatFragment implements SearchFilmsView, FilmsAdapter.OnClickItemListener {

    private static final String TAG = SearchFilmsFragment.class.getSimpleName();
    private static final String SEARCH_STRING_BUNDLE = "search_string_bundle";

    @BindView(R.id.search_et)
    SearchView searchView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @InjectPresenter
    SearchFilmsPresenter searchFilmsPresenter;
    private FilmsAdapter adapter;
    private Unbinder unbinder;
    private OnShowFilmInfoListener onShowFilmInfoListener;

    public static Fragment newInstance() {
        Fragment fragment = new SearchFilmsFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    @OnClick(R.id.search_et)
    void startSearch() {
        searchView.setIconified(false);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_films, container, false);
        unbinder = ButterKnife.bind(this, view);

        initSearchView();
        initFilmsAdapter();

        if (getActivity() != null)
            setOnShowFilmInfoListener((OmdbActivity) getActivity());

        searchFilmsPresenter.init(searchView);

        return view;
    }

    public void setOnShowFilmInfoListener(OnShowFilmInfoListener onShowFilmInfoListener) {
        this.onShowFilmInfoListener = onShowFilmInfoListener;
    }

    @Override
    public void showFilms(List<Film> films) {
        Log.d(TAG, "show films: " + films.size());
        adapter.setData(films);
    }

    @Override
    public void clearData() {
        adapter.clearData();
    }

    /* FilmsAdapter */
    @Override
    public void onItemClicked(@NonNull String imdbID) {
        Log.d(TAG, "onItemClicked " + imdbID);
        onShowFilmInfoListener.onShowFilmInfo(imdbID);
    }

    @Override
    public void showError() {
        adapter.clearData();
        if (getActivity() != null)
            Toast.makeText(getActivity(), getActivity().getString(R.string.smth_went_wrong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void initFilmsAdapter() {
        if (adapter == null) {
            adapter = new FilmsAdapter();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnClickItemListener(this);
    }

    public void setSearchViewTitle() {
        searchView.setQueryHint(App.getAppComponent().getContext().getResources().getString(R.string.app_name));
        searchView.setFocusable(true);
    }

    private void initSearchView() {
        if (getArguments() != null)
            if (!EMPTY.equals(getArguments().getString(SEARCH_STRING_BUNDLE, EMPTY)))
                searchView.setQuery(getArguments().getString(SEARCH_STRING_BUNDLE, EMPTY), false);

        setSearchViewTitle();
        searchView.setIconified(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getArguments() != null) {
            getArguments().putString(SEARCH_STRING_BUNDLE, searchView.getQuery().toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        searchFilmsPresenter.disposeObservable();
    }


    public interface OnShowFilmInfoListener {
        void onShowFilmInfo(@NonNull String idFilm);
    }
}

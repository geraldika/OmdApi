package com.example.omdbapi.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omdbapi.R;
import com.example.omdbapi.main.model.Film;

import java.util.Collections;
import java.util.List;

import static com.example.omdbapi.utils.Constants.EMPTY;

public class FilmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int EMPTY_VIEW = 1;
    private static final String TAG = FilmsAdapter.class.getSimpleName();

    private Context context;
    private List<Film> films;
    private OnClickItemListener onClickItemListener;

    public FilmsAdapter() {
        this.films = Collections.emptyList();
    }

    public void setData(@NonNull List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    @Override
    public int getItemViewType(int position) {
        return films == null || films.size() == 0 ? EMPTY_VIEW : super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return films != null && films.size() > 0 ? films.size() : 1;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return viewType == EMPTY_VIEW ?
                new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false)) :
                new FilmHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FilmHolder) {
            Log.d(TAG, "onBindViewHolder");
            FilmHolder taskHolder = (FilmHolder) holder;
            Film film = films.get(position);
            String title = film.getTitle() != null && !EMPTY.equals(film.getTitle()) ? film.getTitle() : context.getString(R.string.title_not_found);
            taskHolder.titleTv.setText(title);

            taskHolder.filmCv.setOnClickListener((View view) ->
                    onClickItemListener.onItemClicked(films.get(position).getImdbID()));
        }
    }

    public void clearData() {
        setData(Collections.emptyList());
    }

    public interface OnClickItemListener {
        void onItemClicked(@NonNull String imdbID); //tt0096895
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        private EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class FilmHolder extends RecyclerView.ViewHolder {
        private CardView filmCv;
        private TextView titleTv;

        private FilmHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            filmCv = itemView.findViewById(R.id.card_view);
            titleTv = itemView.findViewById(R.id.title_tv);

        }
    }
}
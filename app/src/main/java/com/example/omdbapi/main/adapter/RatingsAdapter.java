package com.example.omdbapi.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.omdbapi.R;
import com.example.omdbapi.main.model.Rating;

import static com.example.omdbapi.utils.Constants.EMPTY;

public class RatingsAdapter extends ArrayAdapter<Rating> {

    private int layoutResourceId;

    public RatingsAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        layoutResourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Rating item = getItem(position);
        View v = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null)
                v = inflater.inflate(layoutResourceId, null);
        } else {
            v = convertView;
        }

        if (v != null && item != null) {

            TextView sourceTv = v.findViewById(R.id.source_tv);
            TextView valueTv = v.findViewById(R.id.value_tv);

            String source = item.getSource() != null ? item.getSource() : EMPTY;
            String value = item.getValue() != null ? item.getValue() : EMPTY;

            sourceTv.setText(source);
            valueTv.setText(value);

        }
        return v;
    }
}
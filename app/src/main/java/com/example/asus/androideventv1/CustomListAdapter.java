package com.example.asus.androideventv1;

/**
 * Created by asus on 31/01/2018.
 */


import android.widget.BaseAdapter;
        import com.example.asus.androideventv1.R;
import com.example.asus.androideventv1.AppController;
import com.example.asus.androideventv1.Event;

import java.util.List;

import com.example.asus.androideventv1.eventoActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.asus.androideventv1.modelo.Evento;

public class CustomListAdapter extends BaseAdapter {
    private eventoActivity activity;
    private LayoutInflater inflater;
    private List<Evento> movieItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(eventoActivity activity, List<Evento> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.representante);
        TextView rating = (TextView) convertView.findViewById(R.id.lugar);
        TextView genre = (TextView) convertView.findViewById(R.id.categoria);
        TextView year = (TextView) convertView.findViewById(R.id.fecha);

        // getting movie data for the row
        Evento m = movieItems.get(position);

        // thumbnail image
        //thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
       // title.setText(m.getTitle());

        // rating
        //rating.setText("Rating: " + String.valueOf(m.getRating()));
/*
        // genre
        String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        genre.setText(genreStr);

        // release year
        year.setText(String.valueOf(m.getYear()));
        */

        return convertView;
    }

}
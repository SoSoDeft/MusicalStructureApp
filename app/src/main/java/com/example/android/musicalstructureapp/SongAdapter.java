package com.example.android.musicalstructureapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {



    public SongAdapter(Activity context, ArrayList<Song> song){

        super(context, 0, song);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        // The list item that will eventually get passed as View
        View listItemView = convertView;

        if(listItemView == null){ // If listItemView exists...
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false); // Inflate custom song_item view

        }
        // Current song object is selected by position listview
        Song currentSong = getItem(position);
        // Song name contained in selected object is set as TextView
        TextView songName = (TextView) listItemView.findViewById(R.id.song_name_list_item);
        songName.setText(currentSong.getSongName());

        // Same is done for song title
        TextView songTitle = (TextView) listItemView.findViewById(R.id.song_title_list_item);
        songTitle.setText(currentSong.getSongTitle());

        // Lastly image resource is set for applicable object
        ImageView icon = (ImageView) listItemView.findViewById(R.id.song_image);
        icon.setImageResource(currentSong.getSongImage());

        return listItemView; // listItemView returned as View


    }

}

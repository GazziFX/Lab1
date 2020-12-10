package com.example.lab1.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.R;
import com.example.lab1.entity.Song;

public class SongListHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView artist;
    private Song songItem;

    public SongListHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.track_title);
        artist = itemView.findViewById(R.id.track_artist);
    }
    public void bind(Song item) {
        title.setText(item.getAudioTitle());
        artist.setText(item.getAudioArtist());
        songItem = item;
    }
    public void setListener(final AdapterClickListener<Song> listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onClick(songItem);
            }
        });
    }
}
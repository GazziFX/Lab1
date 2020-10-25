package com.example.lab1.holders;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.R;
import com.example.lab1.entity.PlaylistItem;

public class PlaylistHolder extends RecyclerView.ViewHolder {
    private TextView playlistName;
    public PlaylistHolder(@NonNull View itemView) {
        super(itemView);
        playlistName = itemView.findViewById(R.id.play_list_name_value);
    }
    public void bind(PlaylistItem item) {
        playlistName.setText(item.getName());
    }
    public void setListener(final OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(getAdapterPosition());
            }
        });
    }
}
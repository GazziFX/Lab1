package com.example.lab1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.lab1.R;
import com.example.lab1.adapters.PlaylistAdapter;
import com.example.lab1.entity.PlaylistItem;
import com.example.lab1.helpers.SwipeHelper;
import com.example.lab1.holders.OnItemClickListener;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 100;
    private Button button;
    private RecyclerView songList;
    private PlaylistAdapter adapter = new PlaylistAdapter();
    private SwipeHelper swipeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songList = findViewById(R.id.song_list);
        songList.setLayoutManager(new LinearLayoutManager(this));
        songList.setAdapter(adapter);
        swipeHelper = new SwipeHelper(songList);
        adapter.restore();
        button = findViewById(R.id.button);
    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCreatePlayListIntent =
                        new Intent(MainActivity.this, EditPlayListActivity.class);
                startActivityForResult(startCreatePlayListIntent, REQUEST_CODE);
            }
        });
        swipeHelper.setListener(new SwipeHelper.SwipeCallback() {
            @Override
            public void onSwipe(int adapterPosition) {
                adapter.remove(adapterPosition);
            }
        });
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PlaylistItem item = adapter.getItem(position);
                Intent startCreatePlayListIntent =
                        new Intent(MainActivity.this, EditPlayListActivity.class);
                startCreatePlayListIntent.putExtra("PLAYLIST_ID", item.getId());
                startCreatePlayListIntent.putExtra("PLAYLIST_NAME", item.getName());
                startActivityForResult(startCreatePlayListIntent, REQUEST_CODE);
                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        button.setOnClickListener(null);
        adapter.setListener(null);
        swipeHelper.setListener(null);
    }

    private void processIntent(Intent intent) {
        String playlist_name = intent.getStringExtra("PLAYLIST_NAME");
        int id = intent.getIntExtra("PLAYLIST_ID", -1);
        if (playlist_name != null && playlist_name.length() > 0) {
            adapter.updateItem(id, playlist_name);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        processIntent(data);
        songList.scrollToPosition(adapter.getItemCount() - 1);
    }
}
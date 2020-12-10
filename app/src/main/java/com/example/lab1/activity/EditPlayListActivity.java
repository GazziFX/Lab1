package com.example.lab1.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.R;
import com.example.lab1.adapters.SongListAdapter;
import com.example.lab1.entity.Song;
import com.example.lab1.helpers.FileHelper;
import com.example.lab1.helpers.permissions.PermissionActivity;
import com.example.lab1.helpers.permissions.PermissionHelper;
import com.example.lab1.holders.AdapterClickListener;

public class EditPlayListActivity extends PermissionActivity {
    private PermissionActivity context;
    private EditText albumName;
    private Button save;
    private final SongListAdapter adapter = new SongListAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_play_list);
        context = this;
        albumName = findViewById(R.id.play_list_name);
        albumName.setText(getIntent().getStringExtra("PLAYLIST_NAME"));
        save = findViewById(R.id.button_save);
        RecyclerView songList = findViewById(R.id.edit_song_list);
        songList.setLayoutManager(new LinearLayoutManager(context));
        songList.setAdapter(adapter);
        PermissionHelper.checkPermission(context, new PermissionCallback() {
            @Override
            public void onGrant() {
                adapter.addData(FileHelper.getAudioFiles(EditPlayListActivity.this));
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        adapter.setListener(new AdapterClickListener<Song>() {
            @Override
            public void onClick(Song song) {
                Toast.makeText(context, song.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = albumName.getText().toString();
                if (text.isEmpty()) {
                    albumName.setError(getString(R.string.playlist_name_error_hint));
                    return;
                }
                Intent result = new Intent();
                result.putExtra("PLAYLIST_NAME", text);
                result.putExtra("PLAYLIST_ID", getIntent().getIntExtra("PLAYLIST_ID", -1));
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        save.setOnClickListener(null);
        adapter.setListener(null);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
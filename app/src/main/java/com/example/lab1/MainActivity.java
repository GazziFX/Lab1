package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 100;
    private Button button;
    private LinearLayout songList;

    private static SharedPreferences getPreferences()
    {
        return Application.getContext().getSharedPreferences("mySharedPrefs", Context.MODE_PRIVATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songList = findViewById(R.id.song_list);
        button = findViewById(R.id.button);
        restoreAlbums(SharedPreferencesHelper.getPlayListNames());

        SharedPreferences pref = getPreferences();
        pref.edit().putString("qq", "привет").putFloat("float", 1.f).apply();
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

        SharedPreferences pref = getPreferences();
        String qq = pref.getString("qq", null);
        float _float = pref.getFloat("float", 0f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        button.setOnClickListener(null);
    }

    private void processIntent(Intent intent) {
        String playlist_name = intent.getStringExtra("PLAYLIST_NAME");
        if (playlist_name != null && playlist_name.length() > 0) {
            addNewAlbum(playlist_name);
            SharedPreferencesHelper.save(playlist_name);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        processIntent(data);
    }

    private void addNewAlbum(String title) {
        LayoutInflater layoutInflater =
                (LayoutInflater) MainActivity
                        .this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = layoutInflater.inflate(R.layout.play_list_item, null);
        TextView playlistName = item.findViewById(R.id.play_list_name_value);
        playlistName.setText(title);
        songList.addView(item);
    }

    private void restoreAlbums(List<String> albums) {
        for (String album : albums) {
            addNewAlbum(album);
        }
    }
}
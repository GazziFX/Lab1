package com.example.lab1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1.R;

public class EditPlayListActivity extends AppCompatActivity {
    private EditText albumName;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_play_list);
        albumName = findViewById(R.id.play_list_name);
        albumName.setText(getIntent().getStringExtra("PLAYLIST_NAME"));
        save = findViewById(R.id.button_save);
    }

    @Override
    protected void onResume() {
        super.onResume();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = albumName.getText().toString();
                if(text.isEmpty()) {
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
    }
}
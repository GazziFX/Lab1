package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditPlayListActivity extends AppCompatActivity {
    private EditText albumName;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_play_list);
        albumName = findViewById(R.id.play_list_name);
        save = findViewById(R.id.button_save);
    }

    @Override
    protected void onResume() {
        super.onResume();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra("PLAYLIST_NAME", albumName.getText().toString());
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
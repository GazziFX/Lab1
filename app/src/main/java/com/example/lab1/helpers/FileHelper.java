package com.example.lab1.helpers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.NonNull;

import com.example.lab1.entity.Song;

import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    @NonNull
    public static List<Song> getAudioFiles(Context context) {
        List<Song> audioArrayList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//Ищем все медиаданные на внешней памяти телефона
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
//Пробегаемся по курсору и сохраняем данные
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                Song modelAudio = new Song();
                modelAudio.setAudioTitle(title);
                modelAudio.setAudioArtist(artist);
                modelAudio.setAudioUri(Uri.parse(url));
                modelAudio.setAudioDuration(duration);
                audioArrayList.add(modelAudio);
            } while (cursor.moveToNext());
        }
        return audioArrayList;
    }
}
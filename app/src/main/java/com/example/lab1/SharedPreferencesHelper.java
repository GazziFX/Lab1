package com.example.lab1;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;
/**
 * Класс позволяющий сохранять и читать плейлисты
 */
public class SharedPreferencesHelper {
    public static final String PLAY_LIST_PREFERENCES = "PLAY_LIST_PREFERENCES";
    public static final String PLAYLIST_NAME_PREFIX = "PLAYLIST_NAME_";
    public static final String SIZE = "size";

    private static SharedPreferences getPreferences() {
        Context context = Application.getContext();
        return context.getSharedPreferences(PLAY_LIST_PREFERENCES, Context.MODE_PRIVATE);
    }
    /**
     * Возвращает список созданных плейлистов
     *
     * @return список плейлистов
     */
    public static List<String> getPlayListNames() {
        SharedPreferences pref = getPreferences();
        int size = pref.getInt(SIZE, 0);
        List<String> playListNames = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String playListName = pref.getString(PLAYLIST_NAME_PREFIX + i, null);
            if (playListName != null)
                playListNames.add(playListName);
        }
        return playListNames;
    }
    /**
     * Сохраняет новый плейлист
     *
     * @param name названиме плейлиста
     */
    public static void save(String name) {
        SharedPreferences pref = getPreferences();
        int size = pref.getInt(SIZE, 0);
        pref.edit()
                .putString(PLAYLIST_NAME_PREFIX + size++, name)
                .putInt(SIZE, size)
                .apply();
    }
}
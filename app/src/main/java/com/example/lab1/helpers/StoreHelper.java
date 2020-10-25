package com.example.lab1.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lab1.Application;
import com.example.lab1.entity.PlaylistItem;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс позволяющий сохранять и читать плейлисты
 */
public class StoreHelper {
    public static final String PLAY_LIST_PREFERENCES = "PLAY_LIST_PREFERENCES";
    public static final String PLAYLIST_NAME_PREFIX = "PLAYLIST_NAME_";
    public static final String PLAYLIST_ID_PREFIX = "PLAYLIST_ID_";
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
    public static List<PlaylistItem> getPlayListItems() {
        SharedPreferences pref = getPreferences();
        int size = pref.getInt(SIZE, 0);
        List<PlaylistItem> playListNames = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String playListName = pref.getString(PLAYLIST_NAME_PREFIX + i, null);
            int id = pref.getInt(PLAYLIST_ID_PREFIX + i, 0);
            if (playListName != null)
                playListNames.add(new PlaylistItem(id, playListName));
        }
        return playListNames;
    }
    /**
     * Сохраняет новый плейлист
     *
     * @param name названиме плейлиста
     */
    public static PlaylistItem save(String name) {
        SharedPreferences pref = getPreferences();
        int size = pref.getInt(SIZE, 0);
        pref.edit()
                .putString(PLAYLIST_NAME_PREFIX + size, name)
                .putInt(PLAYLIST_ID_PREFIX + size, size)
                .putInt(SIZE, size + 1)
                .apply();
        return new PlaylistItem(size, name);
    }
    public static void update(PlaylistItem item) {
        SharedPreferences pref = getPreferences();
        if (pref.contains(PLAYLIST_ID_PREFIX + item.getId())) {
            pref.edit()
                    .putString(PLAYLIST_NAME_PREFIX + item.getId(), item.getName())
                    .apply();
        }
    }
    public static void remove(PlaylistItem item) {
        SharedPreferences pref = getPreferences();
        pref.edit()
                .remove(PLAYLIST_NAME_PREFIX + item.getId())
                .remove(PLAYLIST_ID_PREFIX + item.getId())
                .apply();
    }
}
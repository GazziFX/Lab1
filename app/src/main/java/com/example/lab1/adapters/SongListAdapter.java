package com.example.lab1.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.Application;
import com.example.lab1.R;
import com.example.lab1.entity.Song;
import com.example.lab1.helpers.FileHelper;
import com.example.lab1.holders.AdapterClickListener;
import com.example.lab1.holders.SongListHolder;

import java.util.ArrayList;
import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListHolder> {
    private final List<Song> list = new ArrayList<>();
    private AdapterClickListener<Song> listener;
    @NonNull
    @Override
    public SongListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SongListHolder(inflater.inflate(R.layout.song_list_item, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull SongListHolder holder, int position) {
        holder.bind(list.get(position));
        holder.setListener(listener);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setListener(AdapterClickListener<Song> listener) {
        this.listener = listener;
    }
    /**
     * Заполняет {@link SongListAdapter#list} данными из хранилища
     */
    public void restore() {
        list.addAll(FileHelper.getAudioFiles(Application.getContext()));
        notifyDataSetChanged();// оповещение об изменении списка данных
    }
    /**
     * Удаляет элемент в позиции position
     * @param position позиция элемента в {@link SongListAdapter#list}
     */
    public void remove(int position) {
        //StoreHelper.remove(list.get(position));
        list.remove(position);
        notifyItemRemoved(position); // оповещение об изменении списка данных
        notifyItemRangeChanged(position, list.size());// оповещение об изменении списка данных
    }
    /**
     * Возвращает элмент находищийся на позиции position
     * @param position номер в {@link SongListAdapter#list}
     * @return {@link Song} содержимое плейлиста
     */
    public Song getItem(int position) {
        return list.get(position);
    }
    ///**
    // * Обновляет элемент
    // * @param id идентификатор {@link Song#getAudioUri} ()}
    // * @param name наименование
    // */
    /*public void updateItem(int id, String name) {
        if (id >= 0) {
            Song item = new Song(id, name);
            int index = list.indexOf(item);
            list.get(index).setName(name);
            StoreHelper.update(item);
            notifyDataSetChanged();// оповещение об изменении списка данных
            return;
        }
        addData(name);
    }*/
    public void addData(List<Song> songs) {
        list.addAll(songs);
        notifyDataSetChanged();// оповещение об изменении списка данных
    }
}
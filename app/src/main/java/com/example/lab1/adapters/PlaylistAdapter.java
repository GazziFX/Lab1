package com.example.lab1.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab1.holders.OnItemClickListener;
import com.example.lab1.R;
import com.example.lab1.entity.PlaylistItem;
import com.example.lab1.helpers.StoreHelper;
import com.example.lab1.holders.PlaylistHolder;

import java.util.ArrayList;
import java.util.List;
/**
 * Адаптер для PlaylistHolder
 */
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistHolder> {
    private final List<PlaylistItem> list = new ArrayList<>();
    private OnItemClickListener listener;
    @NonNull
    @Override
    public PlaylistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PlaylistHolder(inflater.inflate(R.layout.play_list_item, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull PlaylistHolder holder, int position) {
        holder.bind(list.get(position));
        holder.setListener(listener);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    /**
     * Заполняет {@link PlaylistAdapter#list} данными из хранилища
     */
    public void restore() {
        list.addAll(StoreHelper.getPlayListItems());
        notifyDataSetChanged();// оповещение об изменении списка данных
    }
    /**
     * Удаляет элемент в позиции position
     * @param position позиция элемента в {@link PlaylistAdapter#list}
     */
    public void remove(int position) {
        StoreHelper.remove(list.get(position));
        list.remove(position);
        notifyItemRemoved(position); // оповещение об изменении списка данных
        notifyItemRangeChanged(position, list.size());// оповещение об изменении списка данных
    }
    /**
     * Возвращает элмент находищийся на позиции position
     * @param position номер в {@link PlaylistAdapter#list}
     * @return {@link PlaylistItem} содержимое плейлиста
     */
    public PlaylistItem getItem(int position) {
        return list.get(position);
    }
    /**
     * Обновляет элемент
     * @param id идентификатор {@link PlaylistItem#getId()}
     * @param name наименование
     */
    public void updateItem(int id, String name) {
        if (id >= 0) {
            PlaylistItem item = new PlaylistItem(id, name);
            int index = list.indexOf(item);
            list.get(index).setName(name);
            StoreHelper.update(item);
            notifyDataSetChanged();// оповещение об изменении списка данных
            return;
        }
        addData(name);
    }
    private void addData(String playlist_name) {
        list.add(StoreHelper.save(playlist_name));
        notifyDataSetChanged();// оповещение об изменении списка данных
    }
}
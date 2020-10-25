package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
public class SwipeHelper {
    private SwipeCallback listener;
    public SwipeHelper(RecyclerView recyclerView) {
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull
                    RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(listener != null){
                    listener.onSwipe(viewHolder.getAdapterPosition());
                }
            }
        });
        touchHelper.attachToRecyclerView(recyclerView);
    }
    public void setListener(SwipeCallback listener) {
        this.listener = listener;
    }
    public interface SwipeCallback {
        void onSwipe(int adapterPosition);
    }
}
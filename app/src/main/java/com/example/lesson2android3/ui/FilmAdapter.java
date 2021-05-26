package com.example.lesson2android3.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson2android3.R;
import com.example.lesson2android3.data.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder>{
    private List<Film> list = new ArrayList<>();
    private Listener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        Log.e("TAG", "getItemCount: " + list.size());
        return list.size();
    }

    public void addList(List<Film> listFilm) {
        this.list = listFilm;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    //=================================ViewHolder======================================
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> listener.onFilmClick(list.get(getAdapterPosition())));
            textView  = itemView.findViewById(R.id.textView);
        }

        public void onBind(Film film) {
            textView.setText(film.getTitle());
        }
    }

    interface Listener{
        void onFilmClick(Film film);
    }
}


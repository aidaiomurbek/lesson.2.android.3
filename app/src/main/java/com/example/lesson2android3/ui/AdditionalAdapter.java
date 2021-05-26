package com.example.lesson2android3.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lesson2android3.R;

import java.util.ArrayList;
import java.util.List;

public class AdditionalAdapter extends RecyclerView.Adapter<AdditionalAdapter.ViewHolder> {

    private List<String> list = new ArrayList<>();

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
        return list.size();
    }

    public void addList(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    //=================================ViewHolder======================================
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        public void onBind(String s) {
            textView.setText(s);
        }
    }
}


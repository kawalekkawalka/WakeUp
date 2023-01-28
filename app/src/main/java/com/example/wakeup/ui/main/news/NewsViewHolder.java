package com.example.wakeup.ui.main.news;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wakeup.R;

public class NewsViewHolder extends RecyclerView.ViewHolder{
    TextView source;
    TextView author;
    TextView title;

    NewsViewHolder(View itemView) {
        super(itemView);
        source = itemView.findViewById(R.id.source);
        author = itemView.findViewById(R.id.author);
        title = itemView.findViewById(R.id.title);

    }
}
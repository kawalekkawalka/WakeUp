package com.example.wakeup.ui.main.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.models.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> data;

    public void setData(List<News> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = data.get(position);
        holder.source.setText(news.getSource().getName());
        holder.author.setText(news.getAuthor());
        holder.title.setText(news.getTitle());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView source;
        TextView author;
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            source = itemView.findViewById(R.id.source);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);

        }
    }
}


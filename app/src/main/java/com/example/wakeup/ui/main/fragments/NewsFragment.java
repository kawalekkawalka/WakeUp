package com.example.wakeup.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.models.News;
import com.example.wakeup.ui.main.news.NewsApiResponse;
import com.example.wakeup.ui.main.news.NewsAdapter;
import com.example.wakeup.ui.main.news.NewsAPI;
import com.example.wakeup.ui.main.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = view.findViewById(R.id.news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getContext());
        recyclerView.setAdapter(adapter);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        NewsAPI newsAPI = retrofit.create(NewsAPI.class);


        Call<NewsApiResponse> call = newsAPI.getNews("us", getString(R.string.news_api));
        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsApiResponse apiResponse = response.body();
                    List<News> firstThreeArticles = apiResponse.getNews();
                    adapter.setData(firstThreeArticles);
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                // handle failure
            }
        });
        return view;
    }
}

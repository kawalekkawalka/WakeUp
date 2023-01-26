package com.example.wakeup.ui.main.news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    @GET("top-headlines")
    Call<NewsApiResponse> getNews(@Query("country") String country, @Query("apiKey") String apiKey);
}

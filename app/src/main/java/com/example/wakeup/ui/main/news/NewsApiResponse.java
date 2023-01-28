package com.example.wakeup.ui.main.news;

import com.example.wakeup.ui.main.models.News;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private int totalResults;
    @SerializedName("articles")
    private List<News> news;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<News> getNews() {
        return news;
    }

}

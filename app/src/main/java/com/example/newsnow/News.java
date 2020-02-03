package com.example.newsnow;

import java.util.List;

public class News {
    private String status;
    private int totalResults;
    private List<NewsItem> articles;

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

    public List<NewsItem> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsItem> articles) {
        this.articles = articles;
    }

    public News(String status, int totalResults, List<NewsItem> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }
}

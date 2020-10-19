package com.example.ostica2.remote;

import com.example.ostica2.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleInterface {

    @GET("/posts")
    Call<List<Article>> getArticles();



}

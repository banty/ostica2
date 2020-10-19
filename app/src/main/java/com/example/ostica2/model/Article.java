package com.example.ostica2.model;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("userId")
    public int userId;
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    public String title;
    @SerializedName("body")
    public String body;

    public Article(int userId, int id, String title, String body){
        this.userId=userId;
        this.id=id;
        this.title=title;
        this.body=body;
    }
}

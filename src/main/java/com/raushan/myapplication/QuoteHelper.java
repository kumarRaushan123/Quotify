package com.raushan.myapplication;

public class QuoteHelper {

   private  String quotes, author, user, likeCount;

    public QuoteHelper() {
    }

    public QuoteHelper(String quotes, String author, String user, String likeCount) {
        this.quotes = quotes;
        this.author = author;
        this.user = user;
        this.likeCount =likeCount;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }
}

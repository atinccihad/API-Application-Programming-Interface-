package com30.pojos;

public class JsonplaceholderTodosPojo {
    /*
    {
    "userId":21,
    "title":"API working..",
    "completed":false,
    "id": 198
    }
     */

    // 1- degiskenleri private yapiyoruz
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    // 2- bu degerlere ulasabilmek icin public getter ve setter methodlari olusturuyoruz
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // 3- parametreli ve parametresiz constructor olusturuyoruz
    public JsonplaceholderTodosPojo() {
    }

    public JsonplaceholderTodosPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // 4- toString methodu olusturuyoruz
    @Override
    public String toString() {
        return "JsonplaceholderTodosPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

}

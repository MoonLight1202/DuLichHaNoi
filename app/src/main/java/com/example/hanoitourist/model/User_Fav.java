package com.example.hanoitourist.model;

public class User_Fav {
    private int id;
    private int id_user;
    private int id_place;
    private int is_like;

    public User_Fav(int id, int id_user, int id_place, int is_like) {
        this.id = id;
        this.id_user = id_user;
        this.id_place = id_place;
        this.is_like = is_like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_place() {
        return id_place;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public int getIs_like() {
        return is_like;
    }

    public void setIs_like(int is_like) {
        this.is_like = is_like;
    }
}

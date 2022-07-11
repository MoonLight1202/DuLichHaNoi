package com.example.hanoitourist.model;

import java.io.Serializable;

public class Places implements Serializable {
    private  int id;
    private  String name;
    private  String avt;
    private  String info;
    private  double lat;
    private  double lon;
    private  String urlwiki;

    public Places(int id, String name, String avt, String info, double lat, double lon, String urlwiki) {
        this.id = id;
        this.name = name;
        this.avt = avt;
        this.info = info;
        this.lat = lat;
        this.lon = lon;
        this.urlwiki = urlwiki;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getAvt() {
        return avt;
    }
    public void setAvt(String avt) {
        this.avt = avt;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getUrlwiki() {
        return urlwiki;
    }

    public void setUrlwiki(String urlwiki) {
        this.urlwiki = urlwiki;
    }
}



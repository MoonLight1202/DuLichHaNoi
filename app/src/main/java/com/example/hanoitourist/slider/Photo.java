package com.example.hanoitourist.slider;

import java.io.Serializable;

public class Photo implements Serializable {
    private String link;

    public Photo(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

package com.example.iwaifu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Style {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private Integer id;

    public Style(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}

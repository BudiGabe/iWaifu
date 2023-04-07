package com.example.iwaifu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class StyleInfo {
    @Expose
    @SerializedName("id")
    private Integer id;

    @Expose
    @SerializedName("icon")
    private String icon;

    @Expose
    @SerializedName("portrait")
    private String portrait;

    @Expose
    @SerializedName("voice_samples")
    private Collection<String> voiceSamples;
}

package com.example.iwaifu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupportedFeature {
    @Expose
    @SerializedName("permitted_synthesis_morphing")
    private String permittedSynthesisMorphing;

    public SupportedFeature(String permittedSynthesisMorphing) {
        this.permittedSynthesisMorphing = permittedSynthesisMorphing;
    }
}

package com.example.iwaifu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class SpeakerInfo {
    @Expose
    @SerializedName("policy")
    private String policy;

    @Expose
    @SerializedName("portrait")
    private String portrait;

    @Expose
    @SerializedName("style_infos")
    private Collection<StyleInfo> styleInfos;

    public SpeakerInfo(String policy, String portrait, Collection<StyleInfo> styleInfos) {
        this.policy = policy;
        this.portrait = portrait;
        this.styleInfos = styleInfos;
    }

    public String getPolicy() {
        return policy;
    }

    public String getPortrait() {
        return portrait;
    }

    public Collection<StyleInfo> getStyleInfos() {
        return styleInfos;
    }

    @Override
    public String toString() {
        return "SpeakerInfo{" +
                "policy='" + policy + '\'' +
                ", styleInfos=" + styleInfos +
                '}';
    }
}

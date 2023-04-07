package com.example.iwaifu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class Speaker {
    @Expose
    @SerializedName("supported_features")
    private SupportedFeature supportedFeatures;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("speaker_uuid")
    private String speakerUuid;
    @Expose
    @SerializedName("styles")
    private Collection<Style> styles;
    @Expose
    @SerializedName("version")
    private String version;

    public Speaker(SupportedFeature supportedFeatures, String name, String speakerUuid, Collection<Style> styles, String version) {
        this.supportedFeatures = supportedFeatures;
        this.name = name;
        this.speakerUuid = speakerUuid;
        this.styles = styles;
        this.version = version;
    }

    public SupportedFeature getSupportedFeatures() {
        return supportedFeatures;
    }

    public String getName() {
        return name;
    }

    public String getSpeakerUuid() {
        return speakerUuid;
    }

    public Collection<Style> getStyles() {
        return styles;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "supportedFeatures=" + supportedFeatures +
                ", name='" + name + '\'' +
                ", speakerUuid='" + speakerUuid + '\'' +
                ", styles=" + styles +
                ", version='" + version + '\'' +
                '}';
    }
}

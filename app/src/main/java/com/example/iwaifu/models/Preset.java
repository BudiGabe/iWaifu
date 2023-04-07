package com.example.iwaifu.models;

public class Preset {
    private Integer id;
    private String name;
    private String speakerUuid;
    private Integer styleId;
    private Float speedScale;
    private Float pitchScale;
    private Float intonationScale;
    private Float volumeScale;
    private Float prePhonemeLength;
    private Float postPhonemeLength;

    public Preset(Integer id, String name, String speakerUuid, Integer styleId, Float speedScale, Float pitchScale, Float intonationScale, Float volumeScale, Float prePhonemeLength, Float postPhonemeLength) {
        this.id = id;
        this.name = name;
        this.speakerUuid = speakerUuid;
        this.styleId = styleId;
        this.speedScale = speedScale;
        this.pitchScale = pitchScale;
        this.intonationScale = intonationScale;
        this.volumeScale = volumeScale;
        this.prePhonemeLength = prePhonemeLength;
        this.postPhonemeLength = postPhonemeLength;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpeakerUuid() {
        return speakerUuid;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public Float getSpeedScale() {
        return speedScale;
    }

    public Float getPitchScale() {
        return pitchScale;
    }

    public Float getIntonationScale() {
        return intonationScale;
    }

    public Float getVolumeScale() {
        return volumeScale;
    }

    public Float getPrePhonemeLength() {
        return prePhonemeLength;
    }

    public Float getPostPhonemeLength() {
        return postPhonemeLength;
    }
}

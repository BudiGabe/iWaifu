package com.example.iwaifu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class VoicevoxAudioQuery {
    @Expose
    @SerializedName("accent_phrases")
    private Collection<AccentPhrase> accentPhrases;
    @Expose
    @SerializedName("speedScale")
    private Float speedScale;
    @Expose
    @SerializedName("pitchScale")
    private Float pitchScale;
    @Expose
    @SerializedName("intonationScale")
    private Float intonationScale;
    @Expose
    @SerializedName("volumeScale")
    private Float volumeScale;
    @Expose
    @SerializedName("prePhonemeLength")
    private Float prePhonemeLength;
    @Expose
    @SerializedName("postPhonemeLength")
    private Float postPhonemeLength;
    @Expose
    @SerializedName("outputSamplingRate")
    private Integer outputSamplingRate;
    @Expose
    @SerializedName("outputStereo")
    private Boolean outputStereo;
    @Expose
    @SerializedName("kana")
    private String kana;

    public VoicevoxAudioQuery(Collection<AccentPhrase> accentPhrases, Float speedScale, Float pitchScale, Float intonationScale, Float volumeScale, Float prePhonemeLength, Float postPhonemeLength, Integer outputSamplingRate, Boolean outputStereo, String kana) {
        this.accentPhrases = accentPhrases;
        this.speedScale = speedScale;
        this.pitchScale = pitchScale;
        this.intonationScale = intonationScale;
        this.volumeScale = volumeScale;
        this.prePhonemeLength = prePhonemeLength;
        this.postPhonemeLength = postPhonemeLength;
        this.outputSamplingRate = outputSamplingRate;
        this.outputStereo = outputStereo;
        this.kana = kana;
    }

    public Collection<AccentPhrase> getAccentPhrases() {
        return accentPhrases;
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

    public Integer getOutputSamplingRate() {
        return outputSamplingRate;
    }

    public Boolean getOutputStereo() {
        return outputStereo;
    }

    public String getKana() {
        return kana;
    }

    @Override
    public String toString() {
        return "VoicevoxAudioQuery{" +
                "accentPhrases=" + accentPhrases +
                ", speedScale=" + speedScale +
                ", pitchScale=" + pitchScale +
                ", intonationScale=" + intonationScale +
                ", volumeScale=" + volumeScale +
                ", prePhonemeLength=" + prePhonemeLength +
                ", postPhonemeLength=" + postPhonemeLength +
                ", outputSamplingRate=" + outputSamplingRate +
                ", outputStereo=" + outputStereo +
                ", kana='" + kana + '\'' +
                '}';
    }
}

package com.example.iwaifu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mora {
    @Expose
    @SerializedName("text")
    private String text;
    @Expose
    @SerializedName("consonant")
    private String consonant;
    @Expose
    @SerializedName("consonant_length")
    private Float consonantLength;
    @Expose
    @SerializedName("vowel")
    private String vowel;
    @Expose
    @SerializedName("vowel_length")
    private Float vowelLength;
    @Expose
    @SerializedName("pitch")
    private Float pitch;

    public Mora(String text, String consonant, Float consonantLength, String vowel, Float vowelLength, Float pitch) {
        this.text = text;
        this.consonant = consonant;
        this.consonantLength = consonantLength;
        this.vowel = vowel;
        this.vowelLength = vowelLength;
        this.pitch = pitch;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getConsonant() {
        return consonant;
    }

    public void setConsonant(String consonant) {
        this.consonant = consonant;
    }

    public Float getConsonantLength() {
        return consonantLength;
    }

    public void setConsonantLength(Float consonantLength) {
        this.consonantLength = consonantLength;
    }

    public String getVowel() {
        return vowel;
    }

    public void setVowel(String vowel) {
        this.vowel = vowel;
    }

    public Float getVowelLength() {
        return vowelLength;
    }

    public void setVowelLength(Float vowelLength) {
        this.vowelLength = vowelLength;
    }

    public Float getPitch() {
        return pitch;
    }

    public void setPitch(Float pitch) {
        this.pitch = pitch;
    }

    @Override
    public String toString() {
        return "Mora{" +
                "text='" + text + '\'' +
                ", consonant='" + consonant + '\'' +
                ", consonantLength=" + consonantLength +
                ", vowel='" + vowel + '\'' +
                ", vowelLength=" + vowelLength +
                ", pitch=" + pitch +
                '}';
    }
}

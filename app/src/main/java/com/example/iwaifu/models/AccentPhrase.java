package com.example.iwaifu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

import kotlin.jvm.internal.SerializedIr;

public class AccentPhrase {
    @Expose
    @SerializedName("moras")
    private Collection<Mora> moras;
    @Expose
    @SerializedName("accent")
    private Integer accent;
    @Expose
    @SerializedName("pause_mora")
    private Mora pauseMora;
    @Expose
    @SerializedName("is_interrogative")
    private Boolean isInterrogative;

    public AccentPhrase(Collection<Mora> moras, Integer accent, Mora pauseMora, Boolean isInterrogative) {
        this.moras = moras;
        this.accent = accent;
        this.pauseMora = pauseMora;
        this.isInterrogative = isInterrogative;
    }

    public Collection<Mora> getMoras() {
        return moras;
    }

    public Integer getAccent() {
        return accent;
    }

    public Mora getPauseMora() {
        return pauseMora;
    }

    public Boolean getInterrogative() {
        return isInterrogative;
    }

    @Override
    public String toString() {
        return "AccentPhrase{" +
                "moras=" + moras +
                ", accent=" + accent +
                ", pauseMora=" + pauseMora +
                ", isInterrogative=" + isInterrogative +
                '}';
    }
}

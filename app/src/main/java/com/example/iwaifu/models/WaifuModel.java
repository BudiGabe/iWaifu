package com.example.iwaifu.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WaifuModel implements Parcelable {
    private List<Integer> styleIds;
    private String name;
    private String portrait;

    public WaifuModel(List<Integer> styleIds, String name, String portrait) {
        this.styleIds = styleIds;
        this.name = name;
        this.portrait = portrait;
    }

    public Collection<Integer> getStyleIds() {
        return styleIds;
    }

    public Integer getRandomStyleId() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, styleIds.size());
        return styleIds.get(randomNum);
    }

    public String getName() {
        return name;
    }

    public String getPortrait() {
        return portrait;
    }

    public Bitmap getPortraitAsBitmap() {
        byte[] decodedString = Base64.decode(portrait, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    @Override
    public String toString() {
        return "WaifuModel{" +
                "styleIds=" + styleIds +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getRandomStyleId());
    }
}

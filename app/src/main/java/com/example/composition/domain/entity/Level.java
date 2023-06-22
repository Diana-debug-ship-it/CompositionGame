package com.example.composition.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public enum Level implements Parcelable {
    TEST,
    EASY,
    NORMAL,
    HARD;


    //это решение от чатгпт
    public static final Creator<Level> CREATOR = new Creator<Level>() {
        @Override
        public Level createFromParcel(Parcel in) {
            return Level.values()[in.readInt()];
        }

        @Override
        public Level[] newArray(int size) {
            return new Level[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(flags);
    }
}

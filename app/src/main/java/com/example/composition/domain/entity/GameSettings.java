package com.example.composition.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;


public class GameSettings implements Parcelable {
     private Integer maxSumValue;
     private Integer minCountOfRightAnswers;
     private Integer minPercentOfRightAnswers;
     private Integer gameTimeInSeconds;

    public GameSettings(Integer maxSumValue,
                        Integer minCountOfRightAnswers,
                        Integer minPercentOfRightAnswers,
                        Integer gameTimeInSeconds) {
        this.maxSumValue = maxSumValue;
        this.minCountOfRightAnswers = minCountOfRightAnswers;
        this.minPercentOfRightAnswers = minPercentOfRightAnswers;
        this.gameTimeInSeconds = gameTimeInSeconds;
    }

    protected GameSettings(Parcel in) {
        if (in.readByte() == 0) {
            maxSumValue = null;
        } else {
            maxSumValue = in.readInt();
        }
        if (in.readByte() == 0) {
            minCountOfRightAnswers = null;
        } else {
            minCountOfRightAnswers = in.readInt();
        }
        if (in.readByte() == 0) {
            minPercentOfRightAnswers = null;
        } else {
            minPercentOfRightAnswers = in.readInt();
        }
        if (in.readByte() == 0) {
            gameTimeInSeconds = null;
        } else {
            gameTimeInSeconds = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (maxSumValue == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(maxSumValue);
        }
        if (minCountOfRightAnswers == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(minCountOfRightAnswers);
        }
        if (minPercentOfRightAnswers == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(minPercentOfRightAnswers);
        }
        if (gameTimeInSeconds == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(gameTimeInSeconds);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GameSettings> CREATOR = new Creator<GameSettings>() {
        @Override
        public GameSettings createFromParcel(Parcel in) {
            return new GameSettings(in);
        }

        @Override
        public GameSettings[] newArray(int size) {
            return new GameSettings[size];
        }
    };

    public Integer getMaxSumValue() {
        return maxSumValue;
    }

    public void setMaxSumValue(Integer maxSumValue) {
        this.maxSumValue = maxSumValue;
    }

    public Integer getMinCountOfRightAnswers() {
        return minCountOfRightAnswers;
    }

    public void setMinCountOfRightAnswers(Integer minCountOfRightAnswers) {
        this.minCountOfRightAnswers = minCountOfRightAnswers;
    }

    public Integer getMinPercentOfRightAnswers() {
        return minPercentOfRightAnswers;
    }

    public void setMinPercentOfRightAnswers(Integer minPercentOfRightAnswers) {
        this.minPercentOfRightAnswers = minPercentOfRightAnswers;
    }

    public Integer getGameTimeInSeconds() {
        return gameTimeInSeconds;
    }

    public void setGameTimeInSeconds(Integer gameTimeInSeconds) {
        this.gameTimeInSeconds = gameTimeInSeconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSettings that = (GameSettings) o;
        return Objects.equals(maxSumValue, that.maxSumValue) && Objects.equals(minCountOfRightAnswers, that.minCountOfRightAnswers) && Objects.equals(minPercentOfRightAnswers, that.minPercentOfRightAnswers) && Objects.equals(gameTimeInSeconds, that.gameTimeInSeconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxSumValue, minCountOfRightAnswers, minPercentOfRightAnswers, gameTimeInSeconds);
    }

    @Override
    public String toString() {
        return "GameSettings{" +
                "maxSumValue=" + maxSumValue +
                ", minCountOfRightAnswers=" + minCountOfRightAnswers +
                ", minPercentOfRightAnswers=" + minPercentOfRightAnswers +
                ", gameTimeInSeconds=" + gameTimeInSeconds +
                '}';
    }


}

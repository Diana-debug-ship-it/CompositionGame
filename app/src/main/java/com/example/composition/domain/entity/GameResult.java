package com.example.composition.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class GameResult implements Parcelable {
    private boolean winner;
    private Integer countOfRightAnswers;
    private Integer countOfQuestions;
    private GameSettings gameSettings;

    public GameResult(boolean winner,
                      Integer countOfRightAnswers,
                      Integer countOfQuestions,
                      GameSettings gameSettings) {
        this.winner = winner;
        this.countOfRightAnswers = countOfRightAnswers;
        this.countOfQuestions = countOfQuestions;
        this.gameSettings = gameSettings;
    }

    protected GameResult(Parcel in) {
        winner = in.readByte() != 0;
        if (in.readByte() == 0) {
            countOfRightAnswers = null;
        } else {
            countOfRightAnswers = in.readInt();
        }
        if (in.readByte() == 0) {
            countOfQuestions = null;
        } else {
            countOfQuestions = in.readInt();
        }
        gameSettings = in.readParcelable(GameSettings.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (winner ? 1 : 0));
        if (countOfRightAnswers == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(countOfRightAnswers);
        }
        if (countOfQuestions == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(countOfQuestions);
        }
        dest.writeParcelable(gameSettings, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GameResult> CREATOR = new Creator<GameResult>() {
        @Override
        public GameResult createFromParcel(Parcel in) {
            return new GameResult(in);
        }

        @Override
        public GameResult[] newArray(int size) {
            return new GameResult[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return winner == that.winner && Objects.equals(countOfRightAnswers, that.countOfRightAnswers) && Objects.equals(countOfQuestions, that.countOfQuestions) && Objects.equals(gameSettings, that.gameSettings);
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "winner=" + winner +
                ", countOfRightAnswers=" + countOfRightAnswers +
                ", countOfQuestions=" + countOfQuestions +
                ", gameSettings=" + gameSettings +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(winner, countOfRightAnswers, countOfQuestions, gameSettings);
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Integer getCountOfRightAnswers() {
        return countOfRightAnswers;
    }

    public void setCountOfRightAnswers(Integer countOfRightAnswers) {
        this.countOfRightAnswers = countOfRightAnswers;
    }

    public Integer getCountOfQuestions() {
        return countOfQuestions;
    }

    public void setCountOfQuestions(Integer countOfQuestions) {
        this.countOfQuestions = countOfQuestions;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }
}

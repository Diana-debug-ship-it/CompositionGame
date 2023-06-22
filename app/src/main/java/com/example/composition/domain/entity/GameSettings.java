package com.example.composition.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class GameSettings implements Serializable {
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

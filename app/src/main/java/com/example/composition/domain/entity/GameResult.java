package com.example.composition.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class GameResult implements Serializable {
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

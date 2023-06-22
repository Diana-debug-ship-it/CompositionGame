package com.example.composition.domain.entity;

import java.util.List;
import java.util.Objects;

public class Question {
    private Integer sum;
    private Integer visibleNumber;
    private List<Integer> options;

    private Integer rightAnswer;

    public Question(Integer sum, Integer visibleNumber, List<Integer> options) {
        this.sum = sum;
        this.visibleNumber = visibleNumber;
        this.options = options;
        rightAnswer = this.sum-this.visibleNumber;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getVisibleNumber() {
        return visibleNumber;
    }

    public void setVisibleNumber(Integer visibleNumber) {
        this.visibleNumber = visibleNumber;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(sum, question.sum) && Objects.equals(visibleNumber, question.visibleNumber) && Objects.equals(options, question.options) && Objects.equals(rightAnswer, question.rightAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sum, visibleNumber, options, rightAnswer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "sum=" + sum +
                ", visibleNumber=" + visibleNumber +
                ", options=" + options +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}

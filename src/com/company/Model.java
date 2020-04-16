package com.company;

public class Model {

    private ScreenObject equation;
    private int topScore = 0;
    private int timerLength;

    public int getTimerLength() {
        return timerLength;
    }

    public void setTimerLength(int timerLength) {
        this.timerLength = timerLength;
    }

    public int getTopScore() {
        return topScore;
    }

    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    public ScreenObject getEquation() {
        return equation;
    }

    public void setEquation(ScreenObject equation) {
        this.equation = equation;
    }

    public void addScore() {
        this.setTopScore(getTopScore() + 1);
        getEquation().generateImagePath();
    }

    public void loseTime(){
        this.setTimerLength(this.getTimerLength() - 5);
    }
}

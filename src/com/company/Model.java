package com.company;

import java.util.ArrayList;

public class Model {

   // ArrayList<ScreenObject> screenObjects = new ArrayList<>();
    private ScreenObject equation;
    private int topScore = 0;
    private int timerLength;
//    public void addObjectToArray(ScreenObject sc){
//        screenObjects.add(sc);
//    }

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
//    public void setEquation(ScreenObject equation) {
//        this.equation = equation;
//    }

    public void addScore() {
        this.setTopScore( getTopScore() + 1);
        getEquation().generateImagePath();
    }
}

package com.company;
// This is the Model class which is used to control data flow inside the game
public class Model {
//  Here we set up the fields
    private ScreenObject equation;
    private int finalScore = 0;
    private int timerLength = 60;
//  This method returns the time
    public int getTimerLength() {
        return timerLength;
    }
//  This method sets the time
    public void setTimerLength(int timerLength) {
        this.timerLength = timerLength;
    }
//  This method returns the final score
    public int getFinalScore() {
        return finalScore;
    }
//  The method sets the final score
    public void setFinalScore(int topScore) {
        this.finalScore = topScore;
    }
//  This method returns the equation
    public ScreenObject getEquation() {
        return equation;
    }
//  This method sets the equation
    public void setEquation(ScreenObject equation) {
        this.equation = equation;
    }
//  This method adds the score and generates new equation
    public void addScore() {
        this.setFinalScore(getFinalScore() + 1);
        getEquation().generateImagePath();
    }
//  This method removes 5 seconds from the current time
    public void loseTime(){
        this.setTimerLength(this.getTimerLength() - 5);
    }
}

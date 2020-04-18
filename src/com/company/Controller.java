package com.company;

import javafx.event.Event;
import javafx.event.EventHandler;

//  This class handles events
public class Controller implements EventHandler<Event> {
    private Model model;
    private View view;
//  This is the constructor for the controller class
    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        initializeListener();
    }
//  Here we initialize the listener for each button
    private void initializeListener() {
        view.getAnswerOne().setOnMouseClicked(this);
        view.getAnswerTwo().setOnMouseClicked(this);
        view.getAnswerThree().setOnMouseClicked(this);
        view.getRetryButton().setOnMouseClicked(this);
    }
//  This is the handle method for each button
    @Override
    public void handle(Event event) {
//      If the first answer is pressed
        if(event.getSource() == view.getAnswerOne()){
//          If the value in on the button matches the name of the equation
            if(view.getAnswerOne().getText().equals(model.getEquation().getImageValue()))
            {
//              Play the correct sound and add the score
                AudioHandler.getInstance().playCorrectSound();
                model.addScore();
            }else{
//              Play incorrect sound and lose 5 seconds from the timer
                AudioHandler.getInstance().playIncorrectSound();
                model.loseTime();
//                model.setTimerLength(model.getTimerLength() - 5);
            }
        }
//      If the second answer is pressed
        if(event.getSource() == view.getAnswerTwo()){
//          If the value in on the button matches the name of the equation
            if(view.getAnswerTwo().getText().equals(model.getEquation().getImageValue()))
            {
//              Play the correct sound and add the score
                AudioHandler.getInstance().playCorrectSound();
                model.addScore();
            }else{
//              Play incorrect sound and lose 5 seconds from the timer
                AudioHandler.getInstance().playIncorrectSound();
                model.loseTime();
//                model.setTimerLength(model.getTimerLength() - 5);
            }
        }
//      If the third answer is pressed
        if(event.getSource() == view.getAnswerThree()){
//          If the value in on the button matches the name of the equation
            if(view.getAnswerThree().getText().equals(model.getEquation().getImageValue()))
            {
//              Play the correct sound and add the score
                AudioHandler.getInstance().playCorrectSound();
                model.addScore();
            }else{
//              Play incorrect sound and lose 5 seconds from the timer
                AudioHandler.getInstance().playIncorrectSound();
                model.loseTime();
//                model.setTimerLength(model.getTimerLength() - 5);
            }
        }
//      If the retry button has been pressed
        if(event.getSource() == view.getRetryButton()){
//          Here we reset the game
            model.setTimerLength(60);
            model.setFinalScore(0);
            view.getRetryButton().setVisible(false);
            view.getGameOverScoreField().setVisible(false);
            View newGameView = new View(view.getRoot(), this.model);
            new Controller(this.model, newGameView);
            model.getEquation().generateImagePath();
        }
    }
}
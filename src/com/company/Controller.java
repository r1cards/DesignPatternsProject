package com.company;

import javafx.event.Event;
import javafx.event.EventHandler;


public class Controller implements EventHandler<Event> {
    private Model model;
    private View view;

    Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        initializeListener();
    }

    private void initializeListener() {
        view.getAnswerOne().setOnMouseClicked(this);
        view.getAnswerTwo().setOnMouseClicked(this);
        view.getAnswerThree().setOnMouseClicked(this);
        view.getRetryButton().setOnMouseClicked(this);
    }
    @Override
    public void handle(Event event) {
        if(event.getSource() == view.getAnswerOne()){
            if(view.getAnswerOne().getText().equals(model.getEquation().getImageValue()))
            {
                model.addScore();
            }else{
                model.setTimerLength(model.getTimerLength() - 5);
            }
        }
        if(event.getSource() == view.getAnswerTwo()){
            if(view.getAnswerTwo().getText().equals(model.getEquation().getImageValue()))
            {
                model.addScore();
            }else{
                model.setTimerLength(model.getTimerLength() - 5);
            }
        }
        if(event.getSource() == view.getAnswerThree()){
            if(view.getAnswerThree().getText().equals(model.getEquation().getImageValue()))
            {
                model.addScore();
            }else{
                model.setTimerLength(model.getTimerLength() - 5);
            }
        }
        if(event.getSource() == view.getRetryButton()){
            view.getRetryButton().setVisible(false);
            view.getGameOverScoreField().setVisible(false);
            new View(view.getRoot(), model);
            model.getEquation().generateImagePath();
            model.setTimerLength(60);
            model.setTopScore(0);
        }
    }
}
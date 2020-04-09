package com.company;

import javafx.event.Event;
import javafx.event.EventHandler;


public class Controller implements EventHandler {
    Model model;
    View view;
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    @Override
    public void handle(Event arg0) {
        // TODO Auto-generated method stub

    }

}


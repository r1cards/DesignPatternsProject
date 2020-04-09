package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    View view;
    Model model;
    Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MVC App");
        Pane root = new Pane();
        stage.setScene(new Scene(root, 1280, 720));
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);

        stage.show();
        model = new Model();
        view = new View(root, model);
        controller = new Controller(model, view);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}

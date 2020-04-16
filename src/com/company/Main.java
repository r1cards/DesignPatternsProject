package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        Button startButton = new Button("Start");
//        Start Button
        startButton.setLayoutX(600);
        startButton.setLayoutY(400);
        startButton.setScaleX(4.5);
        startButton.setScaleY(3);
        startButton.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
        startButton.setStyle("-fx-background-color: orange; -fx-text-fill: white");
        Pane start = new Pane();
        start.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-image: url(/images/backgroundStart.png); -fx-background-repeat: none");
        start.getChildren().addAll(startButton);
        startButton.setOnMouseClicked(event -> {
            Pane root = new Pane();
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
            model = new Model();
            view = new View(root, model);
            ScreenObject screenObject = new ScreenObject(view.getGc(), 275,150);
            model.setEquation(screenObject);
            controller = new Controller(model, view);
        });
        stage.setScene(new Scene(start, 1280, 720));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}

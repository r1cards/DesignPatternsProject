package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
// This is the Main class of the game
public class Main extends Application {
    private View view;
    private Model model;
//  This is the constructor for the Main Class
    public static void main(String[] args) {
        launch(args);
    }
//  Tis method starts the start stage of the game which then if followed by the main stage
    @Override
    public void start(Stage stage) throws Exception {
//      Here we set the name of the stage and its properties
        stage.setTitle("Counting game");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
//      Here we setup the start button
        Button startButton = new Button("Start");
        startButton.setLayoutX(600);
        startButton.setLayoutY(400);
        startButton.setScaleX(4.5);
        startButton.setScaleY(3);
        startButton.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
        startButton.setStyle("-fx-background-color: orange; -fx-text-fill: white");
//      Here we setup the stage for the start screen
        Pane start = new Pane();
//      Here we set the background of the start screen
        start.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-image: url(/images/backgroundStart.png); -fx-background-repeat: none");
        start.getChildren().addAll(startButton);
//      If the start button is pressed we start the main stage of the game
        startButton.setOnMouseClicked(event -> {
//          Setting up the main stage
            Pane root = new Pane();
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
            model = new Model();
            view = new View(root, model);
            ScreenObject screenObject = new ScreenObject(view.getGc(), 275,140);
            model.setEquation(screenObject);
            new Controller(model, view);
        });
//      Here we set the stage for the start screen
        stage.setScene(new Scene(start, 1280, 720));
        stage.show();
//      Here we make sure the game is turned off when the window is closed
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }
}

package com.company;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.Timer;
import java.util.TimerTask;

public class View {
    private Pane root;
    private Model model;
    private Button answerOne, answerTwo, answerThree, retryButton;
   private Canvas canvas;
    private Text timerField, scoreField, gameOverScoreField;
    private GraphicsContext gc;
    private AnimationTimer animationTimer;
   // private int topScore = 0;
    private int correctAnswerPoints = 1;
    private int timerLength = 60;


    View(Pane root, Model model) {
        this.root = root;
        this.model = model;
        root.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
//        Values for answers
        answerOne = new Button("23");
        answerTwo = new Button("11");
        answerThree = new Button("9");
        retryButton = new Button("Retry");
//         Answer 1
        answerOne.setLayoutX(400);
        answerOne.setLayoutY(560);
        answerOne.setScaleX(2.5);
        answerOne.setScaleY(3.8);
//         Answer 2
        answerTwo.setLayoutX(600);
        answerTwo.setLayoutY(560);
        answerTwo.setScaleX(2.5);
        answerTwo.setScaleY(3.8);
//         Answer 3
        answerThree.setLayoutX(800);
        answerThree.setLayoutY(560);
        answerThree.setScaleX(2.5);
        answerThree.setScaleY(3.8);
//        Retry Button
        retryButton.setLayoutX(600);
        retryButton.setLayoutY(470);
        retryButton.setScaleX(3);
        retryButton.setScaleY(2.4);
//         Timer field
        timerField = new Text();
        timerField.setLayoutX(1180);
        timerField.setLayoutY(60);
//        Score field
        scoreField = new Text();
        scoreField.setLayoutX(15);
        scoreField.setLayoutY(45);
        scoreField.setText(Integer.toString(0));
        scoreField.setFont(Font.font("Verdana", FontWeight.MEDIUM, 45));
//        Game over score
        gameOverScoreField = new Text();
        gameOverScoreField.setLayoutX(270);
        gameOverScoreField.setLayoutY(420);
        gameOverScoreField.setFont(Font.font("Verdana", FontWeight.BOLD, 65));
//          Graphics content
        canvas = new Canvas(1200,350);
        gc = canvas.getGraphicsContext2D();
//        Position of the image
      //  ScreenObject screenObject = new ScreenObject(gc, 275,150);
       // model.addObjectToArray(screenObject);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timerField.setText(timerLength+"");
                gameOverScoreField.setText("        Score: "+model.getTopScore());
                if(timerLength >= 10){
                    timerField.setFont(Font.font("Verdana", FontWeight.BOLD, 65));
                }else if(timerLength <= 1){
                    retryButton.setVisible(true);
                    gameOverScoreField.setVisible(true);
                    setMainGameElementsToNotVisible();
                    model.getEquation().gameOver();
                    timer.purge();
                    this.cancel();
                    //        Retry Button
                    retryButton.setOnMouseClicked(e -> {
                        retryButton.setVisible(false);
                        gameOverScoreField.setVisible(false);
                        new View(root, model);
                        timerLength = 60;
                        model.setTopScore(0);
                    });
                }else{
                    timerField.setFont(Font.font("Verdana", FontWeight.BOLD, 65));
                    timerField.setFill(Color.RED);
                }
                timerLength--;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0,1000);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.getEquation().update();
                scoreField.setText(Integer.toString(model.getTopScore()));
//
            }
        };
//
        answerTwo.setOnMouseClicked(event -> {
            if(answerTwo.getText().equals(model.getEquation().getImageValue()))
            {
                model.addScore();
            }else{
                    this.timerLength = this.timerLength - 5;
            }
        });
        answerThree.setOnMouseClicked(event -> {
            if(answerThree.getText().equals(model.getEquation().getImageValue()))
            {
                model.addScore();
            }else{
                    this.timerLength = this.timerLength - 5;
            }
        });
        retryButton.setVisible(false);
        gameOverScoreField.setVisible(false);
        root.getChildren().addAll(gameOverScoreField, retryButton);
        retryButton.setText("RETRY");
        root.getChildren().addAll(answerOne, answerTwo, answerThree, canvas, timerField, scoreField);
        AudioHandler.getInstance().play();
        animationTimer.start();
    }


    public Button getAnswerOne() {
        return answerOne;
    }

    public Button getAnswerTwo() {
        return answerTwo;
    }

    public Button getAnswerThree() {
        return answerThree;
    }

    public Button getRetryButton() {
        return retryButton;
    }

    private void setMainGameElementsToNotVisible(){
        answerOne.setVisible(false);
        answerTwo.setVisible(false);
        answerThree.setVisible(false);
        timerField.setVisible(false);
        scoreField.setVisible(false);
    }

    public GraphicsContext getGc() {
        return gc;
    }

}
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
    private int timerLength = 60;


    View(Pane root, Model model) {
        this.root = root;
        this.model = model;
        root.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-image: url(/images/background.png); -fx-background-repeat: none");
//        Values for answers
        answerOne = new Button("23");
        answerTwo = new Button("11");
        answerThree = new Button("9");
        retryButton = new Button("Retry");
//        Answer 1
        answerOne.setLayoutX(400);
        answerOne.setLayoutY(570);
        answerOne.setScaleX(2.5);
        answerOne.setScaleY(3.8);
        answerOne.setStyle("-fx-background-color: gray; -fx-text-fill: white");
        answerOne.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
//        Answer 2
        answerTwo.setLayoutX(600);
        answerTwo.setLayoutY(570);
        answerTwo.setScaleX(2.5);
        answerTwo.setScaleY(3.8);
        answerTwo.setStyle("-fx-background-color: orange; -fx-text-fill: white");
        answerTwo.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
//        Answer 3
        answerThree.setLayoutX(800);
        answerThree.setLayoutY(570);
        answerThree.setScaleX(2.5);
        answerThree.setScaleY(3.8);
        answerThree.setStyle("-fx-background-color: green; -fx-text-fill: white");
        answerThree.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
//        Retry Button
        retryButton.setLayoutX(590);
        retryButton.setLayoutY(560);
        retryButton.setScaleX(3);
        retryButton.setScaleY(2.4);
        retryButton.setStyle("-fx-background-color: green; -fx-text-fill: white");
        retryButton.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
//        Timer field
        timerField = new Text();
        timerField.setLayoutX(1210);
        timerField.setLayoutY(50);
//        Score field
        scoreField = new Text();
        scoreField.setLayoutX(15);
        scoreField.setLayoutY(53);
        scoreField.setText(Integer.toString(0));
        scoreField.setFont(Font.font("Verdana", FontWeight.MEDIUM, 49));
        scoreField.setStrokeWidth(2);
        scoreField.setStroke(Color.WHITE);
//        Game over score
        gameOverScoreField = new Text();
        gameOverScoreField.setLayoutX(285);
        gameOverScoreField.setLayoutY(420);
        gameOverScoreField.setFont(Font.font("Verdana", FontWeight.BOLD, 65));
        gameOverScoreField.setFill(Color.WHITE);
//        Graphics content
        canvas = new Canvas(1200,350);
        gc = canvas.getGraphicsContext2D();
//        Position of the image
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timerField.setText(timerLength+"");
                timerField.setStrokeWidth(2);
                timerField.setStroke(Color.BLACK);
                timerField.setFill(Color.WHITE);
                gameOverScoreField.setText("               "+model.getTopScore());
                if(timerLength >= 10){
                    timerField.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
                }else if(timerLength <= 0){
                    root.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-image: url(/images/backgroundRetry.png); -fx-background-repeat: none");
                    AudioHandler.getInstance().playRetryScreenSound();
                    retryButton.setVisible(true);
                    gameOverScoreField.setVisible(true);
                    setMainGameElementsToNotVisible();
                    model.getEquation().gameOver();
                    timer.purge();
                    this.cancel();
                    AudioHandler.getInstance().stopAllSounds();
                }else{
                    timerField.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
                    timerField.setStroke(Color.BLACK);
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
            }
        };
        retryButton.setVisible(false);
        gameOverScoreField.setVisible(false);
        root.getChildren().addAll(gameOverScoreField, retryButton);
        retryButton.setText("RETRY");
        root.getChildren().addAll(answerOne, answerTwo, answerThree, canvas, timerField, scoreField);
        AudioHandler.getInstance().playBackground();
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

    public Text getGameOverScoreField() {
        return gameOverScoreField;
    }

    public Pane getRoot() {
        return root;
    }

    public void setTimerLength(int timerLength) {
        this.timerLength = timerLength;
    }
}
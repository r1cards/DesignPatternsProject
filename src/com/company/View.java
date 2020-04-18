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
// This is the view class which is used to add visual elements to the screen
public class View {
//  Here we set the fields
    private Pane root;
    private Model model;
    private Button answerOne, answerTwo, answerThree, retryButton;
    private Canvas canvas;
    private Text timerField, scoreField, gameOverScoreField;
    private GraphicsContext gc;
    private AnimationTimer animationTimer;
//  This is the constructor for the View class
    public View(Pane root, Model model) {
        this.root = root;
        this.model = model;
//      Here we set the background for the main screen
        root.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-image: url(/images/background.png); -fx-background-repeat: none");
//      Here we create and assign values to the buttons
        answerOne = new Button("23");
        answerTwo = new Button("11");
        answerThree = new Button("9");
        retryButton = new Button("Retry");
//      Answer 1
        answerOne.setLayoutX(400);
        answerOne.setLayoutY(570);
        answerOne.setScaleX(2.5);
        answerOne.setScaleY(3.8);
        answerOne.setStyle("-fx-background-color: gray; -fx-text-fill: white");
        answerOne.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
//      Answer 2
        answerTwo.setLayoutX(600);
        answerTwo.setLayoutY(570);
        answerTwo.setScaleX(2.5);
        answerTwo.setScaleY(3.8);
        answerTwo.setStyle("-fx-background-color: orange; -fx-text-fill: white");
        answerTwo.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
//      Answer 3
        answerThree.setLayoutX(800);
        answerThree.setLayoutY(570);
        answerThree.setScaleX(2.5);
        answerThree.setScaleY(3.8);
        answerThree.setStyle("-fx-background-color: green; -fx-text-fill: white");
        answerThree.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
//      Retry Button
        retryButton.setLayoutX(590);
        retryButton.setLayoutY(560);
        retryButton.setScaleX(3);
        retryButton.setScaleY(2.4);
        retryButton.setStyle("-fx-background-color: green; -fx-text-fill: white");
        retryButton.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
//      Timer field
        timerField = new Text();
        timerField.setLayoutX(1210);
        timerField.setLayoutY(50);
//      Score field
        scoreField = new Text();
        scoreField.setLayoutX(15);
        scoreField.setLayoutY(53);
        scoreField.setText(Integer.toString(0));
        scoreField.setFont(Font.font("Verdana", FontWeight.MEDIUM, 49));
        scoreField.setStrokeWidth(2);
        scoreField.setStroke(Color.WHITE);
//      Game over score field
        gameOverScoreField = new Text();
        gameOverScoreField.setLayoutX(285);
        gameOverScoreField.setLayoutY(420);
        gameOverScoreField.setFont(Font.font("Verdana", FontWeight.BOLD, 65));
        gameOverScoreField.setFill(Color.WHITE);
//      Graphics content
        canvas = new Canvas(1200,350);
        gc = canvas.getGraphicsContext2D();
//      Here we create a timer which starts at 60 seconds and goes down to 0
        Timer timer = new Timer();
//      Here we assign tasks for the timer while it is running
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timerField.setText(model.getTimerLength()+"");
                timerField.setStrokeWidth(2);
                timerField.setStroke(Color.BLACK);
                timerField.setFill(Color.WHITE);
                gameOverScoreField.setText("               "+model.getFinalScore());
//              If the timer has more than 10 seconds left
                if(model.getTimerLength() >= 10){
                    timerField.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
//              Here we check if the time is at 0 or less
                }else if(model.getTimerLength() <= 0){
//                  Here we set the background for the the final screen
                    root.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-background-image: url(/images/backgroundRetry.png); -fx-background-repeat: none");
//                  Here we play the final screen sound
                    AudioHandler.getInstance().playRetryScreenSound();
//                  Here we set the visibility for the elements which are on the final screen
                    retryButton.setVisible(true);
                    gameOverScoreField.setVisible(true);
//                  Here we make the main screen elements non visible
                    setMainGameElementsToNotVisible();
//                  Here we change the equation to the game over image
                    model.getEquation().gameOver();
//                  Here we stop the timer
                    timer.purge();
                    this.cancel();
//                  Here we stop all of the main game sounds
                    AudioHandler.getInstance().stopAllSounds();
                }else{
//                  If the timer has less that 10 seconds left we change the color of the text to red
                    timerField.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
                    timerField.setStroke(Color.BLACK);
                    timerField.setFill(Color.RED);
                }
                model.setTimerLength(model.getTimerLength()-1);
            }
        };
//      Here we set the timer speed
        timer.scheduleAtFixedRate(timerTask, 0,1000);
//      Here we update the image and set the final score
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.getEquation().update();
                scoreField.setText(Integer.toString(model.getFinalScore()));
            }
        };
//      Here we make the retry button invisible by default
        retryButton.setVisible(false);
//      Here we set the game over score field invisible by default
        gameOverScoreField.setVisible(false);
//      Here we add the retry button and game over score to the screen
        root.getChildren().addAll(gameOverScoreField, retryButton);
        retryButton.setText("RETRY");
//      Here we add all the main elements to the screen
        root.getChildren().addAll(answerOne, answerTwo, answerThree, canvas, timerField, scoreField);
//      Here we play the background music foe the game
        AudioHandler.getInstance().playBackground();
//      Here we start the animation timer
        animationTimer.start();
    }
//  This method returns the answer one button
    public Button getAnswerOne() {
        return answerOne;
    }
//  This method returns the answer two button
    public Button getAnswerTwo() {
        return answerTwo;
    }
//  This method returns the answer three button
    public Button getAnswerThree() {
        return answerThree;
    }
//  This method returns the retry button
    public Button getRetryButton() {
        return retryButton;
    }
//  This method sets the main game elements to non visible
    private void setMainGameElementsToNotVisible(){
        answerOne.setVisible(false);
        answerTwo.setVisible(false);
        answerThree.setVisible(false);
        timerField.setVisible(false);
        scoreField.setVisible(false);
    }
//  This method returns the Graphics Content
    public GraphicsContext getGc() {
        return gc;
    }
//  This method returns the game over score
    public Text getGameOverScoreField() {
        return gameOverScoreField;
    }
//  This method returns the root
    public Pane getRoot() {
        return root;
    }
}
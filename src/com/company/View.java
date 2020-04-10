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
    Pane root;
    Model model;
    Button b1, b2, b3, b4;
    Canvas canvas;
    Text t1, t2;
    GraphicsContext gc;
    AnimationTimer animationTimer;
    Timer timer;
    int score = 0;
    int timerLength = 60;
    public View(Pane root, Model model) {
        this.root = root;
        this.model = model;
//        Values for answers
        b1 = new Button("23");
        b2 = new Button("11");
        b3 = new Button("9");
        b4 = new Button("Hint");
//         Answer 1
        b1.setLayoutX(400);
        b1.setLayoutY(560);
        b1.setScaleX(2.5);
        b1.setScaleY(3.8);
//         Answer 2
        b2.setLayoutX(600);
        b2.setLayoutY(560);
        b2.setScaleX(2.5);
        b2.setScaleY(3.8);
//         Answer 3
        b3.setLayoutX(800);
        b3.setLayoutY(560);
        b3.setScaleX(2.5);
        b3.setScaleY(3.8);
//        Hint
        b4.setLayoutX(1195);
        b4.setLayoutY(670);
        b4.setScaleX(3);
        b4.setScaleY(2.4);
//         Timer field
        t1 = new Text();
        t1.setLayoutX(1180);
        t1.setLayoutY(60);
//        Score field
        t2 = new Text();
        t2.setLayoutX(15);
        t2.setLayoutY(45);
        t2.setText("score: "+score);
        t2.setFont(Font.font("Verdana", FontWeight.MEDIUM, 45));
//          Graphics content
        canvas = new Canvas(1200,350);
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                t1.setText(timerLength+"");
                if(timerLength >= 10){
                    t1.setFont(Font.font("Verdana", FontWeight.BOLD, 65));
                }else if(timerLength == 0){
                    timerLength = 60;
                    t1.setFill(Color.BLACK);
                }else{
                    t1.setFont(Font.font("Verdana", FontWeight.BOLD, 65));
                    t1.setFill(Color.RED);
                }
                timerLength--;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0,1000);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.getScreenObjects().forEach(screenObject -> {
                    screenObject.update();
                    t2.setText(Integer.toString(score));
                });
            }
        };
        gc = canvas.getGraphicsContext2D();

//        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
//        Position of the image
        ScreenObject screenObject = new ScreenObject(gc, 275,150);
//        screenObject.img = new Image();
        model.addObjectToArray(screenObject);

//        model.addObjectToArray(new ScreenObject(gc, 179,110));
        b1.setOnMouseClicked(event -> {
            if(b1.getText().equals(screenObject.getImageValue()))
            {
                score = timerLength;
            }
        });
        b2.setOnMouseClicked(event -> {
            if(b2.getText().equals(screenObject.getImageValue()))
            {
                score = timerLength;
            }
        });
        b3.setOnMouseClicked(event -> {
            if(b3.getText().equals(screenObject.getImageValue()))
            {
                score = timerLength;
            }
        });

        root.getChildren().addAll(b1, b2, b3, b4, canvas, t1, t2);
        animationTimer.start();
    }
}
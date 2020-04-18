package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;
// This class is used to pick a random equation, it also extends the Game Object class which will draw it on the screen
public class ScreenObject extends GameObject {
//  Here we set the fields
    private String imageValue;
    private Random random;
//  This is the constructor for the ScreenObject class
    public ScreenObject(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        generateImagePath();
    }
//  This method returns the first part of the name for the image which is used as the correct answer later in the game
    public String getImageValue(){
        return imageValue;
    }
//  Generates and returns the first part of the image path
    public String setNewImage(){
        random = new Random();
       int number = random.nextInt(3);
        if(number == 1){
            this.imageValue = "9";
            return "9";
        }else if(number == 2){
            this.imageValue = "11";
            return "11";
        }else{
            this.imageValue = "23";
            return "23";
        }
    }
// Generates and returns the second part of the image path
    public String setImageSelector(){
        random = new Random();
        int number = random.nextInt(8);

        return Integer.toString(number);
    }
//  This method assigns a random image from to the img field
    public void generateImagePath(){
        img = new Image("images/"+setNewImage()+"."+setImageSelector()+".png");
    }
//  This method sets the img filed to the game over image
    public void gameOver(){
        img = new Image("images/gameOver.png");
    }
}

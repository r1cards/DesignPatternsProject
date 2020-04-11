package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class ScreenObject extends GameObject {
    String imageValue;
    Random random;
    public ScreenObject(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        generateImagePath();
    }

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

    public void generateImagePath(){
        img = new Image("images/"+setNewImage()+"."+setImageSelector()+".png");
    }
}

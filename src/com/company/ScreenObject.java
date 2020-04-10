package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class ScreenObject extends GameObject {
    String imageValue = "";
    Random random;
    public ScreenObject(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
//        Problem with url and null pointer exception
        String url = "images/"+setNewImage()+"."+setImageSelector()+".png";
        img = new Image(url);
    }

    public String getImageValue(){
        return imageValue;
    }

    public String setNewImage(){
        random = new Random();
       int number = random.nextInt(2);
        if(number == 0){
            this.imageValue = "9";
            return "9";
        }else if(number == 1){
            this.imageValue = "11";
            return "11";
        }else{
            this.imageValue = "23";
            return "23";
        }
    }

    public String setImageSelector(){
        random = new Random();
        int number = random.nextInt(2);
        return Integer.toString(number);
    }
}

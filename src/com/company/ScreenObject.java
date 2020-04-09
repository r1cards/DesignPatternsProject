package com.company;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ScreenObject extends GameObject {
    String imageName = "";
    public ScreenObject(GraphicsContext gc, double x, double y) {
        super(gc, x, y);
        img = new Image("images/9.png");
        this.imageName = "9";
    }

    public String getImageName(){
        return imageName;
    }
}

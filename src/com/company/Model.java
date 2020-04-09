package com.company;

import java.util.ArrayList;

public class Model {
    ArrayList<ScreenObject> screenObjects = new ArrayList<>();
    public void addObjectToArray(ScreenObject sc){
        screenObjects.add(sc);
    }

    public ArrayList<ScreenObject> getScreenObjects() {
        return screenObjects;
    }
}

package com.company;

import javafx.scene.media.AudioClip;

public class AudioHandler {
    private static AudioHandler instance;
    AudioClip audio;
    public static AudioHandler getInstance() {
        if (instance == null) {
            instance = new AudioHandler();
        }
        return instance;
    }

    private AudioHandler() {
        this.audio = new AudioClip(this.getClass().getResource("audio/MAINTHEME.mp3").toExternalForm());
    }

    public void play(){
        audio.play();
    }
}

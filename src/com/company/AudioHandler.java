package com.company;

import javafx.scene.media.AudioClip;

public class AudioHandler {
    private static AudioHandler instance;
    AudioClip mainAudio, correctAudio, incorrectAudio;
    public static AudioHandler getInstance() {
        if (instance == null) {
            instance = new AudioHandler();
        }
        return instance;
    }

    private AudioHandler() {
        this.mainAudio = new AudioClip(this.getClass().getResource("audio/mainMusic.mp3").toExternalForm());
        this.correctAudio = new AudioClip(this.getClass().getResource("audio/correctSound.mp3").toExternalForm());
        this.incorrectAudio = new AudioClip(this.getClass().getResource("audio/incorrectSound.mp3").toExternalForm());
    }

    public void playBackground(){
        mainAudio.play();
    }
    public void playCorrectSound(){
        correctAudio.play();
    }
    public void playIncorrectSound(){
        incorrectAudio.play();
    }
    public void stopAllSounds(){

    }
}

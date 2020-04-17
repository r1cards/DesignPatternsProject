package com.company;
import javafx.scene.media.AudioClip;
// This class is used to play audio clips in the game
public class AudioHandler {
    private static AudioHandler instance;
//  Here we initialize the fields for each sound
    private AudioClip mainAudio, correctAudio, incorrectAudio, retryScreenSound;
    public static AudioHandler getInstance() {
        if (instance == null) {
            instance = new AudioHandler();
        }
        return instance;
    }
//  Here we assign each field its appropriate sound file
    private AudioHandler() {
        this.mainAudio = new AudioClip(this.getClass().getResource("audio/mainMusic.mp3").toExternalForm());
        this.correctAudio = new AudioClip(this.getClass().getResource("audio/correctSound.mp3").toExternalForm());
        this.incorrectAudio = new AudioClip(this.getClass().getResource("audio/incorrectSound.mp3").toExternalForm());
        this.retryScreenSound = new AudioClip(this.getClass().getResource("audio/retryScreenSound.mp3").toExternalForm());
    }
//  This plays the background music
    public void playBackground(){
        mainAudio.play();
    }
//  This plays the correct sound
    public void playCorrectSound(){
        correctAudio.play();
    }
//  This plays the incorrect sound
    public void playIncorrectSound(){
        incorrectAudio.play();
    }
//  This plays the end screen sounds
    public void playRetryScreenSound(){
        retryScreenSound.play();
    }
//  This stops all main sounds
    public void stopAllSounds(){
        mainAudio.stop();
        correctAudio.stop();
        incorrectAudio.stop();
    }
}

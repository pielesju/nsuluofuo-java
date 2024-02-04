package org.cofezuwo.nsuluofuo.audio;

import javax.sound.sampled.*;
import java.io.IOException;

public class Audio implements Runnable {

    private String audioFile;

    Thread thread;

    public Audio() {

    }

    public void set(String audioFile) {
        this.audioFile = audioFile;
    }

    public synchronized void start() {
        System.out.println("start audio");
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() throws InterruptedException {
        thread.join();
        thread.interrupt();
    }


    @Override
    public void run() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Audio.class.getResourceAsStream("/sounds/sound.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

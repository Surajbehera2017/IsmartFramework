package hex.framework.common;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Media {
	public static synchronized void play(final String fileName) {
		// Note: use .wav files
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
				}
			}
		}).start();

	}
	
	
	public static synchronized void play(final String fileName ,boolean relative) {
		play("C:\\WINDOWS\\Media\\"+fileName);

	}

	public static void buzzer() {
		for (int i = 1; i < 10; i++) {
			play("C:\\WINDOWS\\Media\\ringout.wav");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

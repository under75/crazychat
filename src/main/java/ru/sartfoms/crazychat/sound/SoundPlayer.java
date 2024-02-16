package ru.sartfoms.crazychat.sound;

import java.applet.Applet;
import java.applet.AudioClip;

import org.springframework.stereotype.Component;

@Component
public class SoundPlayer {
	private AudioClip clip = Applet.newAudioClip(getClass().getResource("/sounds/Alarm02.wav"));;

	public void play() {
		clip.play();
	}
}

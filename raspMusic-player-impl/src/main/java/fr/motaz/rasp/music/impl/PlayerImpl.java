package fr.motaz.rasp.music.impl;

import java.util.ArrayList;
import java.util.List;

import fr.motaz.rasp.music.Musique;
import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.impl.handler.PausedHandler;
import fr.motaz.rasp.music.impl.handler.PlayingHandler;
import fr.motaz.rasp.music.impl.handler.StopedHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayerImpl implements Player {

	private static List<MediaPlayer> mPlayers = new ArrayList<MediaPlayer>();

	@Override
	public void stop() {
		if (!mPlayers.isEmpty()) {
			mPlayers.get(0).stop();
		}
	}

	@Override
	public void play() {
		if (!mPlayers.isEmpty()) {
			mPlayers.get(0).play();
		}
	}

	@Override
	public void pause() {
		if (!mPlayers.isEmpty()) {
			mPlayers.get(0).pause();
		}

	}

	@Override
	public void addMusic(Musique musique) {
		System.out.println("addMusic");
		mPlayers.add(createMediaPlayer((musique.getFile().toURI().toString())));
	}

	@Override
	public Media getCurrentMusic() {
		return mPlayers.get(0).getMedia();
	}

	private MediaPlayer createMediaPlayer(String uri) {
		MediaPlayer mediaPlayer = new MediaPlayer(new Media(uri));
		mediaPlayer.setOnPlaying(new PlayingHandler());
		mediaPlayer.setOnPaused(new PausedHandler());
		mediaPlayer.setOnStopped(new StopedHandler());
		return mediaPlayer;
	}

}

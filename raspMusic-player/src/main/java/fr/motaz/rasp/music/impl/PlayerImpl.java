package fr.motaz.rasp.music.impl;

import java.util.ArrayList;
import java.util.List;

import fr.motaz.rasp.music.Music;
import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.PlayerListener;
import fr.motaz.rasp.music.impl.handler.PausedHandler;
import fr.motaz.rasp.music.impl.handler.PlayingHandler;
import fr.motaz.rasp.music.impl.handler.StopedHandler;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayerImpl implements Player {

	private static List<MediaPlayer> mPlayers = new ArrayList<MediaPlayer>();
	private static Integer currentMusicNum = -1;
	private static List<PlayerListener> listeners = new ArrayList<PlayerListener>();

	@Override
	public void stop() {
		getCurrentPlayer().stop();
	}

	@Override
	public void play() {
		getCurrentPlayer().play();
	}

	@Override
	public void pause() {
		getCurrentPlayer().pause();

	}

	@Override
	public void addMusic(Music musique) {
		System.out.println("addMusic");
		mPlayers.add(createMediaPlayer((musique.getFile().toURI().toString())));
		if (currentMusicNum == -1) {
			currentMusicNum++;
		}
	}

	@Override
	public Media getCurrentMusic() {
		return getCurrentPlayer().getMedia();
	}

	private MediaPlayer getCurrentPlayer() {
		if(currentMusicNum>-1){
			return mPlayers.get(currentMusicNum);
		}else{
			return null;
		}
	}

	private MediaPlayer createMediaPlayer(String uri) {
		MediaPlayer mediaPlayer = new MediaPlayer(new Media(uri));
		mediaPlayer.setOnPlaying(new PlayingHandler());
		mediaPlayer.setOnPaused(new PausedHandler());
		mediaPlayer.setOnStopped(new StopedHandler());
		return mediaPlayer;
	}

	public void init() throws Exception {
		System.out.println("Init");
		new JFXPanel();
	}

	public void destroy() throws Exception {
		System.out.println("clean up");
		Platform.exit();
	}

	@Override
	public void next() {
		getCurrentPlayer().stop();
		currentMusicNum++;
		getCurrentPlayer().play();
	}

	@Override
	public void previous() {
		getCurrentPlayer().stop();
		currentMusicNum--;
		getCurrentPlayer().play();
	}

	@Override
	public void addListener(PlayerListener listener) {
		listeners.add(listener);
	}
}

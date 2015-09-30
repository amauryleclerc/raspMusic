package fr.motaz.rasp.music.impl;

import java.util.ArrayList;
import java.util.List;

import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.PlayerListener;
import fr.motaz.rasp.music.model.Music;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class PlayerImpl implements Player {

	private static List<Music> musicList = new ArrayList<Music>();
	private static Integer currentMusicNum = -1;
	private static List<PlayerListener> listeners = new ArrayList<PlayerListener>();

	private static PlayerImpl instance;

	public static PlayerImpl getInstance() {
		if (instance == null) {
			instance = new PlayerImpl();
		}
		return instance;
	}

	@Override
	public void stop() throws Exception {
		getCurrentMusic().getMediaPlayer().stop();

		for (PlayerListener listener : listeners) {
			listener.onStop();
		}
	}

	@Override
	public void play() throws Exception {
		getCurrentMusic().getMediaPlayer().play();
		for (PlayerListener listener : listeners) {
			listener.onPlay(this.getCurrentMusic());
		}
	}

	@Override
	public void pause() throws Exception {
		getCurrentMusic().getMediaPlayer().pause();
		for (PlayerListener listener : listeners) {
			listener.onPause();
		}
	}

	@Override
	public void addMusic(Music music) throws Exception {
		System.out.println("addMusic");
		musicList.add(music);
		if (currentMusicNum == -1) {
			currentMusicNum++;
		}
		for (PlayerListener listener : listeners) {
			listener.onAdd(this.getCurrentMusic());
		}
	}

	@Override
	public Music getCurrentMusic() throws Exception {
		if (currentMusicNum > -1 && currentMusicNum < musicList.size()) {
			return musicList.get(currentMusicNum);
		} else {
			throw new Exception("pas de music");

		}

	}

	public void init() throws Exception {
		System.out.println("Initialisation du player");
		new JFXPanel();

	}

	public void destroy() throws Exception {
		System.out.println("destroy");
		Platform.exit();
	}

	@Override
	public void next() throws Exception {
		this.stop();
		if (musicList.size() > currentMusicNum + 1) {
			currentMusicNum++;
			this.play();
		}
	}

	@Override
	public void previous() throws Exception {
		this.stop();
		if (currentMusicNum > 0) {
			currentMusicNum--;
			this.play();
		}
	}

	@Override
	public void addListener(PlayerListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(PlayerListener listener) {
		listeners.remove(listener);

	}
}

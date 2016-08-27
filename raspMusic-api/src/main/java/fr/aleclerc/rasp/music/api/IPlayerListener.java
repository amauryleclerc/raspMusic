package fr.aleclerc.rasp.music.api;

import fr.aleclerc.rasp.music.api.pojo.Music;

public interface IPlayerListener {
	public void onPlay(Music musicImpl);
	public void onPause();
	public void onStop();
	public void onAdd(Music music);
	public void onRemove(Music music);
	public void onChangeCurrent(Music music);
	public void ontimeChanged(Long currentTime, Long percentage, Long length);
}

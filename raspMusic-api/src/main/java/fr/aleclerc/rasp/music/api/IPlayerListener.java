package fr.aleclerc.rasp.music.api;

public interface IPlayerListener {
	public void onPlay(AMedia musicImpl);
	public void onPause();
	public void onStop();
	public void onAdd(AMedia music);
	public void onRemove(AMedia music);
	public void onChangeCurrent(AMedia music);
	public void ontimeChanged(Long currentTime, Long percentage, Long length);
}

package fr.aleclerc.rasp.music.ws;

import fr.aleclerc.rasp.music.api.EPlayerState;

public enum EAction  {
	TIMECHANGE,PLAY,STOP,PAUSE;
	
	public static EAction fromPlayerState(EPlayerState state){
		return EAction.valueOf(state.name());
	}
}

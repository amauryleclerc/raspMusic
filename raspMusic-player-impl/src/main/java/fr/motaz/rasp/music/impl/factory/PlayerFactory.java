package fr.motaz.rasp.music.impl.factory;

import fr.motaz.rasp.music.Player;
import fr.motaz.rasp.music.impl.PlayerImpl;

public class PlayerFactory {
	private static Player player = null;

	public static Player getInstance() {
		if (player == null) {
			player = new PlayerImpl();
		}

		return player;
	}

}

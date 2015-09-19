package fr.motaz.rasp.music;

import java.io.File;

import fr.motaz.rasp.music.impl.factory.MusiqueFactory;
import fr.motaz.rasp.music.impl.factory.PlayerFactory;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class Launcher {

	public static void main(String[] args) throws InterruptedException {
		new JFXPanel();
		
		Player player = PlayerFactory.getInstance();
//		File file = new File("/Users/Ludovic/Desktop/test.mp3");
		File file = new File("D:\\musique\\musique_itunes\\Music\\Bonobo\\Black Sands\\1-02 Kiara.mp3");
		Musique test = MusiqueFactory.getInstance( file);
		player.addMusic(test);
		player.play();
		Thread.sleep(2000);
		player.pause();
		Thread.sleep(2000);
		player.play();
		Thread.sleep(2000);
		player.stop();
		Platform.exit();
	}

}

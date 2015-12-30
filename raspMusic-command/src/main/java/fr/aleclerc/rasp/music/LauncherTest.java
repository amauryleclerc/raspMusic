package fr.aleclerc.rasp.music;

import java.io.File;
import java.net.URL;

import org.springframework.context.support.ClassPathXmlApplicationContext;



import fr.aleclerc.rasp.music.model.Music;
import fr.aleclerc.rasp.music.player.Player;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class LauncherTest {
	

	public static void main(String[] args) throws Exception {
		File f = new File("D:/musique/musique_itunes/Music/Bonobo/Black Sands/1-02 Kiara.mp3");
	
	System.out.println(f.getAbsolutePath());
		JFXPanel panel = new JFXPanel();
			    final Media media = new Media(f.toURI().toString());
			    final MediaPlayer mediaPlayer = new MediaPlayer(media);
			    mediaPlayer.play();

	
		
	}

}

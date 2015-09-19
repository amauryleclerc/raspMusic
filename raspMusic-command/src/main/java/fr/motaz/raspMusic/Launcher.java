package fr.motaz.raspMusic;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.motaz.rasp.music.Music;
import fr.motaz.rasp.music.Player;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

public class Launcher {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(
						new String[] { "classpath:applicationContext-player.xml"});
		
		
		new JFXPanel();
		
		Player player = (Player) context.getBean("player");
//		File file = new File("/Users/Ludovic/Desktop/test.mp3");
		File file = new File("D:\\musique\\musique_itunes\\Music\\Bonobo\\Black Sands\\1-02 Kiara.mp3");
		Music test = (Music) context.getBean("music");
		test.setFile(file);
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

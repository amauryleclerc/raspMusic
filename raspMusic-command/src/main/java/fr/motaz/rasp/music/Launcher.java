package fr.motaz.rasp.music;

import java.io.File;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {
	private static Player player;

	public static void main(String[] args) throws Exception {
		Music music = null;
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:applicationContext-player.xml" })) {
			player = (Player) context.getBean("player");
			music = (Music) context.getBean("music");
			// File file = new File("/Users/Ludovic/Desktop/test.mp3");
			File file = new File("D:\\musique\\musique_itunes\\Music\\Bonobo\\Black Sands\\1-02 Kiara.mp3");

			music.setFile(file);
			player.addMusic(music);
			player.play();
			Thread.sleep(2000);
			player.pause();
			Thread.sleep(2000);
			player.play();
			Thread.sleep(2000);
			player.stop();
		}

	}

}

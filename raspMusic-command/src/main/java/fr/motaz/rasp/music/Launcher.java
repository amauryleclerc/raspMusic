package fr.motaz.rasp.music;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.motaz.rasp.music.model.Music;

public class Launcher {
	private static Player player;

	public static void main(String[] args) throws Exception {
	
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:applicationContext-player.xml" })) {
			player = (Player) context.getBean("player");
			Music music = (Music) context.getBean("music");
			Music music2 = (Music) context.getBean("music");
			// File file = new File("/Users/Ludovic/Desktop/test.mp3");
//			File file = new File("D:\\musique\\musique_itunes\\Music\\Bonobo\\Black Sands\\1-02 Kiara.mp3");
//			File file2 = new File("D:\\musique\\musique_itunes\\Music\\Bonobo\\Late Night Tales (Mixed)\\05 Baltimore.mp3");
			music.setPath("D:\\musique\\musique_itunes\\Music\\Bonobo\\Black Sands\\1-02 Kiara.mp3");
			music2.setPath("D:\\musique\\musique_itunes\\Music\\Bonobo\\Late Night Tales (Mixed)\\05 Baltimore.mp3");
			player.addMusic(music);
			player.play();
			Thread.sleep(2000);
			player.pause();
			Thread.sleep(2000);
			player.play();
			player.addMusic(music2);
			Thread.sleep(2000);
			player.next();
			Thread.sleep(2000);
			player.previous();
			Thread.sleep(2000);
			player.stop();
		}

	}

}

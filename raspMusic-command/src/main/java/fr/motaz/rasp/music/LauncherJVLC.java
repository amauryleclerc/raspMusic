package fr.motaz.rasp.music;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

public class LauncherJVLC {

	public static void main(String[] args) throws Exception {
		boolean found = new NativeDiscovery().discover();
		System.out.println(found);
		System.out.println(LibVlc.INSTANCE.libvlc_get_version());
		DirectMediaPlayerComponent mediaPlayerComponent;


		mediaPlayerComponent = new DirectMediaPlayerComponent(new BufferFormatCallback() {
			@Override
			public BufferFormat getBufferFormat(int width, int height) {
				 return new RV32BufferFormat(width, height);
			}
		});

//		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
//		mediaPlayerComponent.getMediaPlayer().playMedia("https://www.youtube.com/watch?v=0Bvm9yG4cvs");
		mediaPlayerComponent.getMediaPlayer().playMedia("file:///D:/Bureau/music/04%20Castle%20In%20the%20Snow.mp3");
		Thread.sleep(5000);
		mediaPlayerComponent.getMediaPlayer().pause();
		Thread.sleep(5000);
		mediaPlayerComponent.getMediaPlayer().play();
	}
}

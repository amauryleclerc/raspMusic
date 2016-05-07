package fr.aleclerc.rasp.music.ws.webSocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.aleclerc.rasp.music.model.Music;
import fr.aleclerc.rasp.music.player.PlayerListener;
import fr.aleclerc.rasp.music.player.PlaylistListener;


@Controller
public class WebSocketBroker  implements PlayerListener, PlaylistListener {

	@Autowired
	SimpMessagingTemplate template;
	
	
	protected  static final Logger logger = LogManager.getLogger(WebSocketBroker.class);



	@Override
	public void onAdd(Music music) {
		template.convertAndSend("/player/add", music);

	}

	@Override
	public void onPlay(Music music) {
		template.convertAndSend("/player/play", music);
	}

	@Override
	public void onPause() {
		template.convertAndSend("/player/pause", new Message("pause"));

	}

	@Override
	public void onStop() {
		template.convertAndSend("/player/stop", new Message("stop"));

	}

	@Override
	public void onRemove(Music music) {
		template.convertAndSend("/player/remove", music);
	}

	@Override
	public void onChangeCurrent(Music music) {
		template.convertAndSend("/player/change", music);
	}

	

	@Override
	public void ontimeChanged(Long currentTime, Long percentage, Long length) {
		template.convertAndSend("/player/timechange", new Message("timechange", currentTime, percentage, length));
		
	}

}

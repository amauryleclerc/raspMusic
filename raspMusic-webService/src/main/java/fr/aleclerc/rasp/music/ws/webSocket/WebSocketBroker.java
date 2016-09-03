package fr.aleclerc.rasp.music.ws.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.aleclerc.rasp.music.api.IPlayerListener;
import fr.aleclerc.rasp.music.api.pojo.Music;


@Controller
public class WebSocketBroker  implements IPlayerListener {
	
	private final Logger LOGGER = LoggerFactory.getLogger(WebSocketBroker.class.getClass());
	
	@Autowired
	SimpMessagingTemplate template;
	

	@Override
	public void onAdd(Music music) {
		LOGGER.debug("Send onAdd : {}",music);
		template.convertAndSend("/player/add", music);

	}

	@Override
	public void onPlay(Music music) {
		LOGGER.debug("Send onPlay : {}",music);
		template.convertAndSend("/player/play", music);
	}

	@Override
	public void onPause() {
		LOGGER.debug("Send onPause");
		template.convertAndSend("/player/pause", new Message("pause"));

	}

	@Override
	public void onStop() {
		LOGGER.debug("Send onStop");
		template.convertAndSend("/player/stop", new Message("stop"));

	}

	@Override
	public void onRemove(Music music) {
		LOGGER.debug("Send onRemove : {}",music);
		template.convertAndSend("/player/remove", music);
	}

	@Override
	public void onChangeCurrent(Music music) {
		LOGGER.debug("Send onChangeCurrent : {}",music);
		template.convertAndSend("/player/change", music);
	}

	

	@Override
	public void ontimeChanged(Long currentTime, Long percentage, Long length) {
		LOGGER.debug("Send ontimeChanged : {}",currentTime);
		template.convertAndSend("/player/timechange", new Message("timechange", currentTime, percentage, length));
		
	}

}

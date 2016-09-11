package fr.aleclerc.rasp.music.ws.webSocket;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.aleclerc.rasp.music.api.AMedia;
import fr.aleclerc.rasp.music.api.EPlayerState;
import fr.aleclerc.rasp.music.api.IPlayer;
import fr.aleclerc.rasp.music.api.IPlayerListener;
import fr.aleclerc.rasp.music.ws.EAction;

@Controller
public class WebSocketBroker implements IPlayerListener {

	private final Logger LOGGER = LoggerFactory.getLogger(WebSocketBroker.class.getClass());

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private IPlayer player;

	public WebSocketBroker() {
	}

	@PostConstruct
	public void init() {
		// CHANGE STATE
		player.getStateStream().subscribe(s -> {
			LOGGER.debug("change state : {}", s);
			this.sendFromState(s);
		}, e -> LOGGER.error("change state : {}", e.getMessage()));

		// CHANGE TIME
		player.getTimeStream()//
		.map(t -> new Message(EAction.TIMECHANGE, t.getA(), t.getB()))//
		.subscribe(m -> {
			//Message m =  new Message(EAction.TIMECHANGE, t.getA(), t.getB());
			LOGGER.debug("change time : {}", m);
			template.convertAndSend("/player/timechange", m);
		}, e -> LOGGER.error("change time : {}", e.getMessage()));
	}

	private void sendFromState(EPlayerState state) {
		final AMedia media = player.getCurrentMedia();
		switch (state) {
		case PLAY:
			this.onPlay(media);
			break;
		case STOP:
			this.onStop();
			break;
		case PAUSE:
			this.onPause();
			break;
		default:
			LOGGER.error("pas de correspondance : {}", state);
			break;
		}
	}

	@Override
	public void onAdd(AMedia music) {
		LOGGER.debug("Send onAdd : {}", music);
		template.convertAndSend("/player/add", music);

	}

	@Override
	public void onPlay(AMedia music) {
		LOGGER.debug("Send onPlay : {}", music);
		template.convertAndSend("/player/play", music);
	}

	@Override
	public void onPause() {
		LOGGER.debug("Send onPause");
		template.convertAndSend("/player/pause", new Message(EAction.PAUSE));

	}

	@Override
	public void onStop() {
		LOGGER.debug("Send onStop");
		template.convertAndSend("/player/stop", new Message(EAction.STOP));

	}

	@Override
	public void onRemove(AMedia music) {
		LOGGER.debug("Send onRemove : {}", music);
		template.convertAndSend("/player/remove", music);
	}

	@Override
	public void onChangeCurrent(AMedia music) {
		LOGGER.debug("Send onChangeCurrent : {}", music);
		template.convertAndSend("/player/change", music);
	}

	@Override
	public void ontimeChanged(Long currentTime, Long percentage, Long length) {
		LOGGER.debug("Send ontimeChanged : {}", currentTime);
		// template.convertAndSend("/player/timechange", new
		// Message("timechange", currentTime, percentage, length));

	}

}

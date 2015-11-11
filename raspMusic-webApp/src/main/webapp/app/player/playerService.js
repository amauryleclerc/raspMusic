app
		.service(
				'PlayerService',
				[
						'Player',
						'$timeout',
						function(Player, $timeout) {
							var _onReceiveData = _.noop;

							var Service = {};
							Service.currentMusic = Player.getCurrent();
							Service.playlist = Player.getPlaylist();
							Service.state = Player.getState();
							var port =80;
							if(document.location.port){
								port = document.location.port;
							}
							var url = "ws://"
								+ document.location.hostname + ":"
								+ port
								+ "/api/websocket";
//							document.location.pathname
							console.log(url);
							var ws = new WebSocket(url);

							ws.onopen = function() {
								console.log("WebSocket : open");
							};
							ws.onclose = function() {
								console.log("WebSocket : close");
							};
							ws.onerror = function() {
								console.log(" WebSocket : error");
							};
							ws.onmessage = function(message) {
								console.log(" WebSocket : message");
								listener(JSON.parse(message.data));
							};

							function listener(data) {
								$timeout(function() {

									if (data.action === "PLAY") {
										_.assign(Service.currentMusic,
												data.music);
									
										_.assign(Service.state, data);
									}
										else if (data.action === "CHANGE") {
											_.assign(Service.currentMusic,
													data.music);
										
									} else if (data.action === "ADD") {
										if (typeof Service.currentMusic.title == "undefined") {
											_.assign(Service.currentMusic,
													data.music);
										}
										Service.playlist.push(data.music);
									} else if (data.action === "STOP") {
										_.assign(Service.state, data);
									} else if (data.action === "PAUSE") {
										_.assign(Service.state, data);
									} else if (data.action === "REMOVE") {
										for ( var num in Service.playlist) {
											if (Service.playlist[num].position === data.music.position) {
												Service.playlist.splice(num, 1);
											}
										}

									}

								});
							}

							return Service;
						} ])
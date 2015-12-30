app
		.service(
				'PlayerService',
				[
						'Player',
						'$timeout',
						'$websocket',
						function(Player, $timeout, $websocket) {
							var _onReceiveData = _.noop;
							var port = 80;
							if (document.location.port) {
								port = document.location.port;
							}
							var url = "ws://" + document.location.hostname
									+ ":" + port + "/api/websocket";
							console.log(url);
							var ws = $websocket.$new({
								url : url,
								reconnect : true
							});
							var Service = {};
							Service.currentMusic = Player.getCurrent();
							Service.playlist = Player.getPlaylist();
							Service.state = Player.getState();
						

							// document.location.pathname
						
//							var ws = new WebSocket(url);
						
						    ws.$on('$open', function () {
								console.log("WebSocket : open");
							});
						    ws.$on('$close', function () {
								console.log("WebSocket : close");
							});
						    ws.$on('$error', function () {
								console.log("WebSocket : error");
							});
						
							ws.$on('$message', function (message) {
								console.log(" WebSocket : message");
								console.log(message);
								listener(message);
							});

							function listener(data) {
								$timeout(function() {

									if (data.action === "PLAY") {
										_.assign(Service.currentMusic,
												data.music);

										_.assign(Service.state, data);
									} else if (data.action === "CHANGE") {
										_.assign(Service.currentMusic,
												data.music);

									} else if (data.action === "ADD") {
										if (typeof Service.currentMusic.title == "undefined") {
											_.assign(Service.currentMusic,
													data.music);
										}
										console.log(data.music);
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
								 else if (data.action === "TIMECHANGE") {
									 Service.currentMusic.currentTime = data.currentTime;
									 Service.currentMusic.percentage =  (data.currentTime/ Service.currentMusic.length)/10 ;
									
								}


								});
							}

							return Service;
						} ])
app.service('PlayerService', [ 'Player',  '$timeout' ,function(Player, $timeout) {
	var _onReceiveData = _.noop;

	
    var Service = {};
    Service.currentMusic = Player.getCurrent();
    Service.playlist = Player.getPlaylist();
    Service.state = Player.getState();
   
    var ws = new WebSocket("ws://" + document.location.hostname + ":" + document.location.port
    		+ document.location.pathname + "api/websocket");
	
    ws.onopen = function(){  
        console.log("Connexion");  
    };
    ws.onclose = function(){  
        console.log("close");  
    };
    ws.onerror = function(){  
        console.log("error");  
    };
    ws.onmessage = function(message) {
        listener(JSON.parse(message.data));
    };



    function listener(data) {
      $timeout(function () {
    
    	  if(data.action === "PLAY"){
    		  console.log("play")
    		  _.assign(Service.currentMusic, data.music);
    		  _.assign(Service.state , data);
    		 
        	  console.log(Service.currentMusic);
        	  console.log(Service.state);
    	  }else if(data.action === "ADD"){
    		  console.log("add");
    		  if(typeof Service.currentMusic.title == "undefined"){
    			  _.assign(Service.currentMusic, data.music);
    			  console.log(Service.currentMusic);
    		  }
    		   Service.playlist.push(data.music);
    	  }else if(data.action === "STOP"){
    		  console.log("stop");
       		  _.assign(Service.state , data);
    	  }else if(data.action === "PAUSE"){
    		  console.log("pause");
    		  _.assign(Service.state , data);
    	  }
      });
    }


    return Service;
}])
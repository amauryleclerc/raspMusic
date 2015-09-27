app.service('PlayerService', [  '$timeout' ,function($timeout) {
	var _onReceiveData = _.noop;

	
    var Service = {};
    Service.currentMusic = {};
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
    		  _.assign(Service.currentMusic, data.music);
        	  console.log(Service.currentMusic);
    	  }else if(data.action === "STOP"){
    		  console.log("stop");
    		  delete  Service.currentMusic.title;
    		  delete  Service.currentMusic.artist;
    		  delete  Service.currentMusic.album;
    	  }
    
      });
	
    }


    return Service;
}])
package fr.aleclerc.rasp.music.ws;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.aleclerc.rasp.music.ws.webSocket.WebSocketConfig;
import fr.aleclerc.rasp.music.ws.webSocket.WebSocketServer;

@WebListener
public class SpringInit implements ServletContextListener {
	protected  static final Logger logger = LogManager.getLogger(SpringInit.class);
    private final static String SERVER_CONTAINER_ATTRIBUTE = "javax.websocket.server.ServerContainer";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	logger.info("Initialisation du context");
        ServletContext context = sce.getServletContext();
        context.setInitParameter("log4jConfiguration", "log4j.xml");
        
        final ServerContainer serverContainer = (ServerContainer) context.getAttribute(SERVER_CONTAINER_ATTRIBUTE);
        try {
            serverContainer.addEndpoint(new WebSocketConfig(WebSocketServer.class, "/websocket"));
        } catch (DeploymentException e) {
        	logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Destruction du context");
		
	}
}
package fr.motaz.rasp.music.ws;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;

import fr.motaz.rasp.music.ws.webSocket.WebSocketConfig;
import fr.motaz.rasp.music.ws.webSocket.WebSocketServer;

@WebListener
public class SpringInit implements ServletContextListener {

    private final static String SERVER_CONTAINER_ATTRIBUTE = "javax.websocket.server.ServerContainer";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	System.out.println("Initialisation du context");
        ServletContext container = sce.getServletContext();

        final ServerContainer serverContainer = (ServerContainer) container.getAttribute(SERVER_CONTAINER_ATTRIBUTE);
        try {
            serverContainer.addEndpoint(new WebSocketConfig(WebSocketServer.class, "/websocket"));
        } catch (DeploymentException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Destruction du context");
		
	}
}
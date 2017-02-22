package websockets.ws_tyrus.server;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/echo")
public class EchoEndpoint {
	
    @OnMessage
    public String onMessage(String message, Session session) {
    	System.out.println("Got message: " + message);
    	return message;
    }
    
}

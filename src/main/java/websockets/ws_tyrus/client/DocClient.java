package websockets.ws_tyrus.client;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

public class DocClient {
    private static CountDownLatch messageLatch;
    private static final String SENT_MESSAGE = "Hello World";

    public static void main(String [] args){
        try {
            messageLatch = new CountDownLatch(3);

            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

            ClientManager client = ClientManager.createClient();
            client.connectToServer(new Endpoint() {

                @Override
                public void onOpen(Session session, EndpointConfig config) {
                	System.out.println("Starting client with Endpoint...");
                    try {
                        session.addMessageHandler(new MessageHandler.Whole<String>() {

                            public void onMessage(String message) {
                                System.out.println("Client got message: " + message);
                                messageLatch.countDown();
                            }
                        });
                        System.out.println("Droping initial message... ");
                        session.getBasicRemote().sendText(SENT_MESSAGE);
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, cec, new URI("ws://localhost:8025/websockets/echo"));
            
            messageLatch.await(10, TimeUnit.SECONDS);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
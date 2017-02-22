package websockets.ws_tyrus.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.RunnableScheduledFuture;

import org.glassfish.tyrus.server.Server;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )    {        
    	
    	System.out.println( "Hello WebSockets!" );    	
    	new App().runServer();
    	
    }
    
    public void runServer() {
        Server server = new Server("localhost", 8025, "/websockets", null, EchoEndpoint.class);

        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Websocket server started.");
            reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
    
}

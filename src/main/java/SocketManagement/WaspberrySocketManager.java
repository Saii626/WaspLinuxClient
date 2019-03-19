package SocketManagement;

import Resources.Resources;
import URLs.Url;
import WebsocketMessageHandling.WebsocketMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WaspberrySocketManager {

    private static Logger logger = LoggerFactory.getLogger(WaspberrySocketManager.class.getSimpleName());
    private static WebSocketConnector connector;

    public static void connect() {
        Thread websocketThread = new Thread(() -> {
            logger.debug("Starting new websocket Thread");
            try {
                connector = new WebSocketConnector.Builder()
                        .setEndpointURI(new URI(Url.TEST_DEVICE.getUrl()))
                        .setOnWebsocketMessageListener((conn, msg) -> {
                            WebsocketMessageHandler.handleMessage(msg);
                        })
                        .setOnWebsocketCloseListener(con -> {
                            try {
                                Thread.sleep(Resources.WEBSOCKET_RECONNECT_TIMEOUT);
                                con.connect();
                            } catch (InterruptedException | IOException e) {
                                e.printStackTrace();
                            } catch (DeploymentException e) {
                                e.printStackTrace();
                                try {
                                    Thread.sleep(Resources.WEBSOCKET_RECONNECT_TIMEOUT);
                                    connect();
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }

                        })
                        .build();

                connector.connect();
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            } catch (DeploymentException e) {
                e.printStackTrace();
                // If deployment error occurred, retry after some time
                try {
                    Thread.sleep(Resources.WEBSOCKET_RECONNECT_TIMEOUT);
                    connect();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        websocketThread.setName("waspberrySocket");
        websocketThread.start();
    }

    public static void sendMessage(String msg) {
        connector.sendMessage(msg);
    }

}

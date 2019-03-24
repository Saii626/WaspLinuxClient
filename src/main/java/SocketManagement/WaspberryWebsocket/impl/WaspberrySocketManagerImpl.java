package SocketManagement.WaspberryWebsocket.impl;

import Resources.Resources;
import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandler;
import SocketManagement.WaspberryWebsocket.WaspberrySocketManager;
import SocketManagement.Websocket.WebSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WaspberrySocketManagerImpl implements WaspberrySocketManager {

    private Logger logger = LoggerFactory.getLogger(WaspberrySocketManagerImpl.class.getSimpleName());

    private WebSocketConnector webSocketConnector;
    private WaspberryMessageHandler messageHandler;

    public WaspberrySocketManagerImpl(WebSocketConnector connector, WaspberryMessageHandler messageHandler) {
        this.webSocketConnector = connector;
        this.messageHandler = messageHandler;
    }

    @Override
    public void connect() {
        webSocketConnector.setReconnectTimeout(Resources.WEBSOCKET_RECONNECT_TIMEOUT);
        webSocketConnector.setWebsocketMessageListener((conn, msg) -> messageHandler.handleMessage(msg));
        webSocketConnector.setWebsocketCloseListener(conn -> {
            try {
                Thread.sleep(Resources.WEBSOCKET_RECONNECT_TIMEOUT.toMillis());
                conn.connect();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        Thread websocketThread = new Thread(() -> {
            logger.debug("Starting new websocket Thread");
            try {
                webSocketConnector.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        websocketThread.setName("waspberrySocket");
        websocketThread.start();
    }

    @Override
    public void send(String msg) {
        webSocketConnector.sendMessage(msg);
    }

}

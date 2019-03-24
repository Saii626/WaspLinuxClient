package SocketManagement.WaspberryWebsocket;

import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandler;
import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandlerModule;
import SocketManagement.WaspberryWebsocket.impl.WaspberrySocketManagerImpl;
import SocketManagement.Websocket.WebSocketConnector;
import URLs.Url;
import dagger.Module;
import dagger.Provides;

import java.net.URI;
import java.net.URISyntaxException;

@Module(includes = WaspberryMessageHandlerModule.class)
public class WaspberryWebsocketModule {

    @Provides
    @WaspberryWebsocketScope
    static WaspberrySocketManager getSocketManager(WebSocketConnector connector, WaspberryMessageHandler messageHandler) {
        return new WaspberrySocketManagerImpl(connector, messageHandler);
    }

    @Provides
    @WaspberryWebsocketScope
    static URI getUri() {
        try {
            return new URI(Url.DEVICE.getUrl());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}

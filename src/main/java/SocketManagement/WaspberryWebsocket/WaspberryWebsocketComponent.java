package SocketManagement.WaspberryWebsocket;

import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandler;
import dagger.Component;

@WaspberryWebsocketScope
@Component(modules = WaspberryWebsocketModule.class)
public interface WaspberryWebsocketComponent {

    WaspberrySocketManager getSocketManager();

    WaspberryMessageHandler getHandler();
}

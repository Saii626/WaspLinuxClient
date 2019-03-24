package SocketManagement.WaspberryWebsocket;

import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandler;
import SocketManagement.WaspberryWebsocket.impl.WaspberrySocketManagerImpl;
import SocketManagement.Websocket.WebSocketConnector;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WaspberrySocketManager_Factory implements Factory<WaspberrySocketManagerImpl> {
  private final Provider<WebSocketConnector> connectorProvider;

  private final Provider<WaspberryMessageHandler> messageHandlerProvider;

  public WaspberrySocketManager_Factory(
      Provider<WebSocketConnector> connectorProvider,
      Provider<WaspberryMessageHandler> messageHandlerProvider) {
    this.connectorProvider = connectorProvider;
    this.messageHandlerProvider = messageHandlerProvider;
  }

  @Override
  public WaspberrySocketManagerImpl get() {
    return new WaspberrySocketManagerImpl(connectorProvider.get(), messageHandlerProvider.get());
  }

  public static WaspberrySocketManager_Factory create(
      Provider<WebSocketConnector> connectorProvider,
      Provider<WaspberryMessageHandler> messageHandlerProvider) {
    return new WaspberrySocketManager_Factory(connectorProvider, messageHandlerProvider);
  }

  public static WaspberrySocketManagerImpl newWaspberrySocketManager(
      WebSocketConnector connector, WaspberryMessageHandler messageHandler) {
    return new WaspberrySocketManagerImpl(connector, messageHandler);
  }
}

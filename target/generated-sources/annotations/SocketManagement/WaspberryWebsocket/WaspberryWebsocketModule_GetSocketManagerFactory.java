package SocketManagement.WaspberryWebsocket;

import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandler;
import SocketManagement.Websocket.WebSocketConnector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WaspberryWebsocketModule_GetSocketManagerFactory
    implements Factory<WaspberrySocketManager> {
  private final Provider<WebSocketConnector> connectorProvider;

  private final Provider<WaspberryMessageHandler> messageHandlerProvider;

  public WaspberryWebsocketModule_GetSocketManagerFactory(
      Provider<WebSocketConnector> connectorProvider,
      Provider<WaspberryMessageHandler> messageHandlerProvider) {
    this.connectorProvider = connectorProvider;
    this.messageHandlerProvider = messageHandlerProvider;
  }

  @Override
  public WaspberrySocketManager get() {
    return proxyGetSocketManager(connectorProvider.get(), messageHandlerProvider.get());
  }

  public static WaspberryWebsocketModule_GetSocketManagerFactory create(
      Provider<WebSocketConnector> connectorProvider,
      Provider<WaspberryMessageHandler> messageHandlerProvider) {
    return new WaspberryWebsocketModule_GetSocketManagerFactory(
        connectorProvider, messageHandlerProvider);
  }

  public static WaspberrySocketManager proxyGetSocketManager(
      WebSocketConnector connector, WaspberryMessageHandler messageHandler) {
    return Preconditions.checkNotNull(
        WaspberryWebsocketModule.getSocketManager(connector, messageHandler),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

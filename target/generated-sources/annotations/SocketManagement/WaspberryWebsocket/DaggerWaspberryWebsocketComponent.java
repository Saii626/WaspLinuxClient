package SocketManagement.WaspberryWebsocket;

import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandler;
import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandlerModule;
import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandlerModule_GetGsonFactory;
import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandlerModule_GetHandlerFactory;
import SocketManagement.WaspberryMessageHandler.WaspberryMessageHandlerModule_GetHandlersFactory;
import SocketManagement.Websocket.WebSocketConnector;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerWaspberryWebsocketComponent implements WaspberryWebsocketComponent {
  private DaggerWaspberryWebsocketComponent() {}

  public static Builder builder() {
    return new Builder();
  }

  public static WaspberryWebsocketComponent create() {
    return new Builder().build();
  }

  private WebSocketConnector getWebSocketConnector() {
    return new WebSocketConnector(WaspberryWebsocketModule_GetUriFactory.proxyGetUri());
  }

  @Override
  public WaspberrySocketManager getSocketManager() {
    return WaspberryWebsocketModule_GetSocketManagerFactory.proxyGetSocketManager(
        getWebSocketConnector(), getHandler());
  }

  @Override
  public WaspberryMessageHandler getHandler() {
    return WaspberryMessageHandlerModule_GetHandlerFactory.proxyGetHandler(
        WaspberryMessageHandlerModule_GetGsonFactory.proxyGetGson(),
        WaspberryMessageHandlerModule_GetHandlersFactory.proxyGetHandlers());
  }

  public static final class Builder {
    private Builder() {}

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder waspberryWebsocketModule(WaspberryWebsocketModule waspberryWebsocketModule) {
      Preconditions.checkNotNull(waspberryWebsocketModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder waspberryMessageHandlerModule(
        WaspberryMessageHandlerModule waspberryMessageHandlerModule) {
      Preconditions.checkNotNull(waspberryMessageHandlerModule);
      return this;
    }

    public WaspberryWebsocketComponent build() {
      return new DaggerWaspberryWebsocketComponent();
    }
  }
}

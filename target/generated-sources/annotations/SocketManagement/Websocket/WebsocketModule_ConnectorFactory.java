package SocketManagement.Websocket;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.net.URI;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WebsocketModule_ConnectorFactory implements Factory<WebSocketConnector> {
  private final WebsocketModule module;

  private final Provider<URI> uriProvider;

  public WebsocketModule_ConnectorFactory(WebsocketModule module, Provider<URI> uriProvider) {
    this.module = module;
    this.uriProvider = uriProvider;
  }

  @Override
  public WebSocketConnector get() {
    return proxyConnector(module, uriProvider.get());
  }

  public static WebsocketModule_ConnectorFactory create(
      WebsocketModule module, Provider<URI> uriProvider) {
    return new WebsocketModule_ConnectorFactory(module, uriProvider);
  }

  public static WebSocketConnector proxyConnector(WebsocketModule instance, URI uri) {
    return Preconditions.checkNotNull(
        instance.connector(uri), "Cannot return null from a non-@Nullable @Provides method");
  }
}

package SocketManagement.Websocket;

import dagger.internal.Factory;
import java.net.URI;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WebSocketConnector_Factory implements Factory<WebSocketConnector> {
  private final Provider<URI> endpointURIProvider;

  public WebSocketConnector_Factory(Provider<URI> endpointURIProvider) {
    this.endpointURIProvider = endpointURIProvider;
  }

  @Override
  public WebSocketConnector get() {
    return new WebSocketConnector(endpointURIProvider.get());
  }

  public static WebSocketConnector_Factory create(Provider<URI> endpointURIProvider) {
    return new WebSocketConnector_Factory(endpointURIProvider);
  }

  public static WebSocketConnector newWebSocketConnector(URI endpointURI) {
    return new WebSocketConnector(endpointURI);
  }
}

package SocketManagement.WaspberryWebsocket;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.net.URI;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WaspberryWebsocketModule_GetUriFactory implements Factory<URI> {
  private static final WaspberryWebsocketModule_GetUriFactory INSTANCE =
      new WaspberryWebsocketModule_GetUriFactory();

  @Override
  public URI get() {
    return proxyGetUri();
  }

  public static WaspberryWebsocketModule_GetUriFactory create() {
    return INSTANCE;
  }

  public static URI proxyGetUri() {
    return Preconditions.checkNotNull(
        WaspberryWebsocketModule.getUri(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

package SocketManagement.WaspberryMessageHandler;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WaspberryMessageModule_GetHandlersFactory
    implements Factory<List<MessageHandler>> {
  private static final WaspberryMessageModule_GetHandlersFactory INSTANCE =
      new WaspberryMessageModule_GetHandlersFactory();

  @Override
  public List<MessageHandler> get() {
    return proxyGetHandlers();
  }

  public static WaspberryMessageModule_GetHandlersFactory create() {
    return INSTANCE;
  }

  public static List<MessageHandler> proxyGetHandlers() {
    return Preconditions.checkNotNull(
        WaspberryMessageHandlerModule.getHandlers(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

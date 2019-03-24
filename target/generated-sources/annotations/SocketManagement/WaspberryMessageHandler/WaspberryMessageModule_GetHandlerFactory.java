package SocketManagement.WaspberryMessageHandler;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WaspberryMessageModule_GetHandlerFactory
    implements Factory<WaspberryMessageHandler> {
  private final Provider<Gson> gsonProvider;

  private final Provider<List<MessageHandler>> listProvider;

  public WaspberryMessageModule_GetHandlerFactory(
      Provider<Gson> gsonProvider, Provider<List<MessageHandler>> listProvider) {
    this.gsonProvider = gsonProvider;
    this.listProvider = listProvider;
  }

  @Override
  public WaspberryMessageHandler get() {
    return proxyGetHandler(gsonProvider.get(), listProvider.get());
  }

  public static WaspberryMessageModule_GetHandlerFactory create(
      Provider<Gson> gsonProvider, Provider<List<MessageHandler>> listProvider) {
    return new WaspberryMessageModule_GetHandlerFactory(gsonProvider, listProvider);
  }

  public static WaspberryMessageHandler proxyGetHandler(Gson gson, List<MessageHandler> list) {
    return Preconditions.checkNotNull(
        WaspberryMessageHandlerModule.getHandler(gson, list),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

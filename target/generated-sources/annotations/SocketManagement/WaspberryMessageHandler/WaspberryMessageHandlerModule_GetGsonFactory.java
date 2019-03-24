package SocketManagement.WaspberryMessageHandler;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WaspberryMessageHandlerModule_GetGsonFactory implements Factory<Gson> {
  private static final WaspberryMessageHandlerModule_GetGsonFactory INSTANCE =
      new WaspberryMessageHandlerModule_GetGsonFactory();

  @Override
  public Gson get() {
    return proxyGetGson();
  }

  public static WaspberryMessageHandlerModule_GetGsonFactory create() {
    return INSTANCE;
  }

  public static Gson proxyGetGson() {
    return Preconditions.checkNotNull(
        WaspberryMessageHandlerModule.getGson(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

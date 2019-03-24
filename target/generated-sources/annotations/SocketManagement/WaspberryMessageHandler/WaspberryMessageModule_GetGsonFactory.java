package SocketManagement.WaspberryMessageHandler;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class WaspberryMessageModule_GetGsonFactory implements Factory<Gson> {
  private static final WaspberryMessageModule_GetGsonFactory INSTANCE =
      new WaspberryMessageModule_GetGsonFactory();

  @Override
  public Gson get() {
    return proxyGetGson();
  }

  public static WaspberryMessageModule_GetGsonFactory create() {
    return INSTANCE;
  }

  public static Gson proxyGetGson() {
    return Preconditions.checkNotNull(
        WaspberryMessageHandlerModule.getGson(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

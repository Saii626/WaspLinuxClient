package ConfigurationManagement.impl.ConfigFile;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ConfigFileModule_GetConfigGsonFactory implements Factory<Gson> {
  private static final ConfigFileModule_GetConfigGsonFactory INSTANCE =
      new ConfigFileModule_GetConfigGsonFactory();

  @Override
  public Gson get() {
    return proxyGetConfigGson();
  }

  public static ConfigFileModule_GetConfigGsonFactory create() {
    return INSTANCE;
  }

  public static Gson proxyGetConfigGson() {
    return Preconditions.checkNotNull(
        ConfigFileModule.getConfigGson(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

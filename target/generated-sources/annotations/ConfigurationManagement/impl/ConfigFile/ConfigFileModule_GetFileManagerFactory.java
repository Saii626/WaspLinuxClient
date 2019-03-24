package ConfigurationManagement.impl.ConfigFile;

import ConfigurationManagement.ConfigurationFileManager;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ConfigFileModule_GetFileManagerFactory
    implements Factory<ConfigurationFileManager> {
  private final Provider<File> fileProvider;

  private final Provider<Gson> gsonProvider;

  public ConfigFileModule_GetFileManagerFactory(
      Provider<File> fileProvider, Provider<Gson> gsonProvider) {
    this.fileProvider = fileProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public ConfigurationFileManager get() {
    return proxyGetFileManager(fileProvider.get(), gsonProvider.get());
  }

  public static ConfigFileModule_GetFileManagerFactory create(
      Provider<File> fileProvider, Provider<Gson> gsonProvider) {
    return new ConfigFileModule_GetFileManagerFactory(fileProvider, gsonProvider);
  }

  public static ConfigurationFileManager proxyGetFileManager(File file, Gson gson) {
    return Preconditions.checkNotNull(
        ConfigFileModule.getFileManager(file, gson),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}

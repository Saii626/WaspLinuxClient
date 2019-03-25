package ConfigurationManagement;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ExternalDependenciesModule_GetConfigFileFactory implements Factory<File> {
  private final ExternalDependenciesModule module;

  public ExternalDependenciesModule_GetConfigFileFactory(ExternalDependenciesModule module) {
    this.module = module;
  }

  @Override
  public File get() {
    return proxyGetConfigFile(module);
  }

  public static ExternalDependenciesModule_GetConfigFileFactory create(
      ExternalDependenciesModule module) {
    return new ExternalDependenciesModule_GetConfigFileFactory(module);
  }

  public static File proxyGetConfigFile(ExternalDependenciesModule instance) {
    return Preconditions.checkNotNull(
        instance.getConfigFile(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

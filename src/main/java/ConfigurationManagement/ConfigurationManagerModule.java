package ConfigurationManagement;

import ConfigurationManagement.impl.ConfigFile.ConfigFileModule;
import ConfigurationManagement.impl.ConfigManager.ConfigManagerModule;
import Resources.Resources;
import dagger.Module;
import dagger.Provides;

import java.io.File;

@Module(includes = {ConfigFileModule.class, ConfigManagerModule.class})
public class ConfigurationManagerModule {

    @Provides
    @ConfigurationScope
    static File configFile() {
        return Resources.CONFIG_FILE;
    }
}

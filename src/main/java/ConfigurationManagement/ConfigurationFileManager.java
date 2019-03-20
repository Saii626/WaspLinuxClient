package ConfigurationManagement;

import java.util.Map;

public interface ConfigurationFileManager {

    void createDefaultConfigFile();
    Map<ConfigKey, ConfigurationModel> ingestConfigurationFile();
    void updateConfigurationFile(Map<ConfigKey, ConfigurationModel> configurations);
}

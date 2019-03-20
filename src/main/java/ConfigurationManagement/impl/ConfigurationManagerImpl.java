package ConfigurationManagement.impl;

import ConfigurationManagement.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConfigurationManagerImpl implements ConfigurationManager {


    private Map<ConfigKey, List<OnConfigurationChange>> observerMap;
    private File configFile;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private Map<ConfigKey, ConfigurationModel> inMemoryConfigurations;
    private ConfigurationFileManager configurationFileManager;

    public ConfigurationManagerImpl(File file) {
        configFile = file;
        observerMap = new HashMap<>();

//        configurationFileManager = new ConfigurationFileManager(){}

        inMemoryConfigurations = configurationFileManager.ingestConfigurationFile();
    }

    @Override
    public <T> Optional<T> get(ConfigKey key) {
        ConfigurationModel model = inMemoryConfigurations.getOrDefault(key, null);
        return Optional.ofNullable(model)
    }

    @Override
    public <T> void put(ConfigKey key, T value) {

    }

    @Override
    public void put(ConfigKey key) {

    }

    @Override
    public <T> void addOnConfigurationChangeListener(ConfigKey key, OnConfigurationChange<T> listener) {

    }

    @Override
    public <T> void removeOnConfigurationChangeListener(ConfigKey key, OnConfigurationChange<T> listener) {

    }
}

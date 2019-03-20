package ConfigurationManagement;

import java.util.Optional;

public interface ConfigurationManager {

    <T> Optional<T> get(ConfigKey key);

    <T> void put(ConfigKey key, T value);

    void put(ConfigKey key);

    <T> void addOnConfigurationChangeListener(ConfigKey key, OnConfigurationChange<T> listener);

    <T> void removeOnConfigurationChangeListener(ConfigKey key, OnConfigurationChange<T> listener);
}

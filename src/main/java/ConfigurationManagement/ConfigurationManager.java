package ConfigurationManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ConfigurationManager {

    private Properties properties;
    private Map<ConfigKey, List<OnConfigurationChange>> observerMap;
    private File configFile;
    private FileChannel fileChannel;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public ConfigurationManager(File config) throws IOException {
        observerMap = new HashMap<>();
        configFile = config;
        this.ingestConfigFile();
    }

    // Private APIs
    private void ingestConfigFile() throws IOException {
        if (!configFile.exists()) {
            logger.debug("Configuration file does not exist. Creating with default values");
            this.createAndIngestConfigFileWithDefaultValues();
            return;
        }

        logger.debug("Reading and loading config file");
        this.fileChannel = FileChannel.open(configFile.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE);
        this.properties = new Properties();
        this.properties.load(Channels.newReader(fileChannel, "UTF-8"));
    }

    private void createAndIngestConfigFileWithDefaultValues() throws IOException {
        Files.createDirectory(configFile.toPath().getParent());
        Files.createFile(configFile.toPath());
        this.properties = new Properties();

        this.fileChannel = FileChannel.open(configFile.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE);

        for (ConfigKey key : ConfigKey.values()) {
            this.put(key, key.getDefaultValue());
        }

        this.syncConfigurationFile();
    }

    private void notifyListenersForKey(ConfigKey configKey, String oldVal, String newVal) {
        List<OnConfigurationChange> list = observerMap.getOrDefault(configKey, new ArrayList<>());
        list.forEach(listener -> listener.onConfigurationChange(oldVal, newVal));
    }

    // Public APIs
    public void put(ConfigKey key, String value) {
        logger.debug("Setting {}: {}", key.getKey(), value);
        this.notifyListenersForKey(key, get(key).orElse(""), value == null ? "" : value);
        this.properties.setProperty(key.getKey(), value == null ? "" : value);
    }

    public synchronized void syncConfigurationFile() throws IOException {
        Writer writer = Channels.newWriter(this.fileChannel, "UTF-8");
        this.properties.store(writer, "Application configuration File");
    }

    public Optional<String> get(ConfigKey key) {
        String val = properties.getProperty(key.getKey());
        return (val!=null && val.length() > 0) ? Optional.of(val) : Optional.empty();
    }

    public void put(ConfigKey key) throws IOException {
        this.put(key, "");
    }

    public void addOnConfigurationChangeListener(ConfigKey key, OnConfigurationChange listener) {
        List<OnConfigurationChange> list = observerMap.getOrDefault(key, new ArrayList<>());
        list.add(listener);
        observerMap.put(key, list);
    }

    public void removeOnConfigurationChangeListener(ConfigKey key, OnConfigurationChange listener) {
        List<OnConfigurationChange> list = observerMap.getOrDefault(key, null);
        if (list == null || !list.contains(listener)) {
            return;
        }
        list.remove(listener);
        observerMap.put(key, list);
    }
}

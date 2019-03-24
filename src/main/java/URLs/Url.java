package URLs;

import ConfigurationManagement.MissingConfigurationValue;
import ConfigurationManagement.ConfigKey;
import ConfigurationManagement.ConfigurationManager;
import ConfigurationManagement.SerializerDeserializerClassMismatchException;
import MainApplication.MainApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public enum Url {

    SOCKET("/socket"),
    DEVICE("/socket/test");

    private String url;

    private static BaseUrl base_url = BaseUrl.REMOTE;
    private static Logger logger = LoggerFactory.getLogger(Url.class.getSimpleName());

    private static ConfigurationManager configurationManager = MainApplication.getConfigurationManager();

    private static void changeBaseUrl(BaseUrl base_url) {
        logger.debug("Setting base url to {}", base_url);
        Url.base_url = base_url;
    }

    static {
        try {
            String baseurl = configurationManager.get(ConfigKey.BASE_URL, String.class)
                    .orElseThrow(() -> new MissingConfigurationValue(ConfigKey.BASE_URL));

            changeBaseUrl(BaseUrl.valueOf(baseurl));

            configurationManager.addOnConfigurationChangeListener(ConfigKey.BASE_URL, String.class,
                    (oldVal , newVal) -> changeBaseUrl(BaseUrl.valueOf(newVal)));

            Optional<String> deviceName = configurationManager.get(ConfigKey.DEVICE_NAME, String.class);
            if (deviceName.isPresent()) {
                DEVICE.url = "/socket/".concat(deviceName.get());
                logger.debug("Setting device name to {}", deviceName.get());
            } else {
                logger.warn("No device name set in configuration file. Might be an error");
            }

        } catch (MissingConfigurationValue | SerializerDeserializerClassMismatchException e) {
            e.printStackTrace();
        }
    }

    Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return Url.base_url.getUrl().concat(url);
    }

}

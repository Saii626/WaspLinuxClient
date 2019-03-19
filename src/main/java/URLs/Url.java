package URLs;

import ConfigurationManagement.ConfigKey;
import ConfigurationManagement.ConfigurationManager;
import MainApplication.MainApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Url {

    SOCKET("/socket"),
    TEST_DEVICE("/socket/test");

    private String url;

    private static BaseUrl base_url = BaseUrl.REMOTE;
    private static ConfigurationManager configurationManager = MainApplication.instance.getConfigurationManager();
    private static Logger logger = LoggerFactory.getLogger(Url.class.getSimpleName());

    private static void changeBaseUrl(BaseUrl base_url) {
        logger.debug("Setting base url to {}", base_url);
        Url.base_url = base_url;
    }

    static {
        try {
            String baseurl = configurationManager.get(ConfigKey.BASE_URL)
                    .orElseThrow(() -> new BaseUrlNotConfigured("Base url is not set in config file"));

            changeBaseUrl(BaseUrl.valueOf(baseurl));

            configurationManager.addOnConfigurationChangeListener(ConfigKey.BASE_URL, (oldVal , newVal) -> changeBaseUrl(BaseUrl.valueOf(newVal)));
        } catch (BaseUrlNotConfigured e) {
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

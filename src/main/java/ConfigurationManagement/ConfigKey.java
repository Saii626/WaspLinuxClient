package ConfigurationManagement;

public enum ConfigKey {
    PID("pid", ""),
    BASE_URL("base_url", "REMOTE"),
    TEST("test", "tttt");

    private String key;
    private String default_value;

    ConfigKey(String key, String default_value) {
        this.key = key;
        this.default_value = default_value;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return default_value;
    }
}

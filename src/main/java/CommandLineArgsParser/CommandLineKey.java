package CommandLineArgsParser;

public enum CommandLineKey {
    b("-b"),
    t("-t");

    private String key;

    CommandLineKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

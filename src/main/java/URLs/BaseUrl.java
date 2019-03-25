package URLs;

public enum BaseUrl {

    REMOTE("wss://saikat.app"),
    LOCAL("ws://192.168.100.2"),
    SELF("ws://127.0.0.1");

    private String url;

    BaseUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

package ConfigurationManagement;

public class ConfigurationModel<T> {
    private Class<T> serializer;
    private T data;

    public Class<T> getSerializer() {
        return serializer;
    }

    public T getData() {
        return data;
    }

    public ConfigurationModel(T data, Class<T> serializer) {
        this.data = data;
        this.serializer = serializer;
    }
}

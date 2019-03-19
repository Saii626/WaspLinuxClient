package WebsocketMessageHandling;

public interface MessageHandler<T extends MessageModel> {

    Class<T> handlesMessageOfType();

    void onMessage(T t);
}

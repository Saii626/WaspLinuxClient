package SocketManagement;

public interface OnWebsocketMessageListener {

    void onWebsocketMessage(WebSocketConnector connector, String message);
}

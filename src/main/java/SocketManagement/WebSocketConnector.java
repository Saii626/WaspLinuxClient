package SocketManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

@ClientEndpoint
public class WebSocketConnector {

    private Session session;
    private Optional<OnWebsocketMessageListener> websocketMessageListener;
    private Optional<OnWebsocketOpenListener> websocketOpenListener;
    private Optional<OnWebsocketCloseListener> websocketCloseListener;
    private URI uri;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private WebSocketConnector(URI endpointURI,
                               Optional<OnWebsocketOpenListener> onWebsocketOpenListener,
                               Optional<OnWebsocketMessageListener> onWebsocketMessageListener,
                               Optional<OnWebsocketCloseListener> onWebsocketCloseListener) {

        this.websocketMessageListener = onWebsocketMessageListener;
        this.websocketCloseListener = onWebsocketCloseListener;
        this.websocketOpenListener = onWebsocketOpenListener;
        this.uri = endpointURI;
    }

    public void connect() throws IOException, DeploymentException {
        logger.debug("Connecting to websocket");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, this.uri);
    }

    @OnOpen
    public void onOpen(Session userSession) {
        logger.debug("Opening websocket");
        this.session = userSession;
        websocketOpenListener.ifPresent(listener -> listener.onWebsocketOpen(this));
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        logger.debug("Closing websocket due to {}", reason.getReasonPhrase());
        this.session = null;
        websocketCloseListener.ifPresent(listener -> listener.onWebsocketClose(this));
    }

    @OnMessage
    public void onMessage(String message) {
        logger.debug("message received from websocket");
        logger.debug("message: {}", message);
        websocketMessageListener.ifPresent(listener -> listener.onWebsocketMessage(this, message));
    }

    @OnError
    public void onError(Throwable err) throws Throwable {
        logger.error("Error occurred in websocket connection");
        throw err;
    }

    public void sendMessage(String message) {
        logger.debug("sending message to websocket");
        this.session.getAsyncRemote().sendText(message);
    }

    public static class Builder {
        private URI endpointURI;
        private OnWebsocketOpenListener onWebsocketOpenListener;
        private OnWebsocketMessageListener onWebsocketMessageListener;
        private OnWebsocketCloseListener onWebsocketCloseListener;

        public Builder setEndpointURI(URI endpointURI) {
            this.endpointURI = endpointURI;
            return this;
        }

        public Builder setOnWebsocketOpenListener(OnWebsocketOpenListener onWebsocketOpenListener) {
            this.onWebsocketOpenListener = onWebsocketOpenListener;
            return this;
        }

        public Builder setOnWebsocketMessageListener(OnWebsocketMessageListener onWebsocketMessageListener) {
            this.onWebsocketMessageListener = onWebsocketMessageListener;
            return this;
        }

        public Builder setOnWebsocketCloseListener(OnWebsocketCloseListener onWebsocketCloseListener) {
            this.onWebsocketCloseListener = onWebsocketCloseListener;
            return this;
        }

        public WebSocketConnector build() {
            return new WebSocketConnector(endpointURI,
                    Optional.ofNullable(onWebsocketOpenListener),
                    Optional.ofNullable(onWebsocketMessageListener),
                    Optional.ofNullable(onWebsocketCloseListener));
        }
    }
}

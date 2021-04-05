package de.pschiessle.mmtutoren.client.network;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class MMWebsocketClient extends WebSocketClient {

    private final MessageListener listener;

    public MMWebsocketClient(URI serverUri, MessageListener listener) {
        super(serverUri);
        this.listener = listener;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("ON OPEN=" + handshakedata.getHttpStatusMessage());
    }

    @Override
    public void onMessage(String message) {
        this.listener.onMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}

package de.pschiessle.mmtutoren.client.network;

import java.net.URI;

public class Networker {
    private final String url;

    private MMWebsocketClient client;

    public Networker(String url, MessageListener listener) {
        this.url = url;
        this.client = new MMWebsocketClient(URI.create(url), listener);
    }

    public void connect(){
        if(client != null) client.connect();
    }

    public void close(){
        if(client != null) client.close();
        client = null;
    }
}

package com.xp.mvp_retrofit.event;

public class NetworkChangeEvent {
    public boolean isConnected;

    public NetworkChangeEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }
}

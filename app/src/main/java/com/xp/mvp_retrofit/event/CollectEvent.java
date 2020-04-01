package com.xp.mvp_retrofit.event;

public class CollectEvent {
    public boolean isCollect;
    public int id;

    public CollectEvent(boolean isCollect, int id) {
        this.isCollect = isCollect;
        this.id = id;
    }
}

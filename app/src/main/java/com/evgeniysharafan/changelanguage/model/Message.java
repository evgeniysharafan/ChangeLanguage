package com.evgeniysharafan.changelanguage.model;

import android.support.annotation.NonNull;

public class Message implements Comparable<Message> {

    public String text;
    public long timeMillis;
    public boolean isIncoming;
    public boolean isUnread;

    public Message(String text, long timeMillis, boolean isIncoming, boolean isUnread) {
        this.text = text;
        this.timeMillis = timeMillis;
        this.isIncoming = isIncoming;
        this.isUnread = isUnread;
    }

    @Override
    public int compareTo(@NonNull Message another) {
        // reverse
        return timeMillis < another.timeMillis ? 1 : (timeMillis > another.timeMillis ? -1 : 0);
    }


}

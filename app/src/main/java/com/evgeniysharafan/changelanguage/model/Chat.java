package com.evgeniysharafan.changelanguage.model;

import android.support.annotation.NonNull;

import java.util.List;

public class Chat implements Comparable<Chat> {

    public String name;
    public List<Message> messages;

    public Chat(String name, List<Message> messages) {
        this.name = name;
        this.messages = messages;
    }

    @Override
    public int compareTo(@NonNull Chat another) {
        // reverse
        return messages.get(0).timeMillis < another.messages.get(0).timeMillis ? 1 :
                (messages.get(0).timeMillis > another.messages.get(0).timeMillis ? -1 : 0);
    }

}

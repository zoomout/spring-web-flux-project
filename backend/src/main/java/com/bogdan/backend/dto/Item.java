package com.bogdan.backend.dto;

public class Item {

    private long index;
    private String time;

    public Item(long index, String time) {
        this.index = index;
        this.time = time;
    }

    public long getIndex() {
        return index;
    }

    public String getTime() {
        return time;
    }
}

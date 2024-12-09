package org.example;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<Item> items;
    private MyProcess process;

    public Calendar() {
        items = new ArrayList<>();
    }

    public void addItem(Sources sources, Devices devices) {
        items.add(new Item(sources, devices));
    }

    public void start() {

    }
}

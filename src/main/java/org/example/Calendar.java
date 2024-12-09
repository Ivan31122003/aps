package org.example;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private List<Device> devices;
    private MyProcess process;
    private Integer index;

    public Calendar() {
        devices = new ArrayList<>();
        index = 0;
    }

    public void addItem(Device device) {
        devices.add(device);
    }

    public Device getDevice() {
        return devices.get(index);
    }

    public void incrementIndex() {
        ++index;
    }

    public Boolean isItem() {
        return index < devices.size();
    }

    public Integer getSize() {
        return devices.size();
    }

    public Integer getIndex() {
        return index;
    }
}

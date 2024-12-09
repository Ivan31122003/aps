package org.example;

import java.util.ArrayList;

public class Devices {
    private List<Device> devices;

    public Devices(Integer numberOfDevices) {
        devices = new ArrayList<>();
        for (int i = 0; i < numberOfDevices; ++i) {
            devices.add(new Device());
        }
    }
}

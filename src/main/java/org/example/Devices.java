package org.example;


import java.util.ArrayList;
import java.util.List;

public class Devices {
    private List<Device> devices;
    public Integer devicesNumber;
    public Integer index;

    public Devices(Integer devicesNumber) {
        devices = new ArrayList<>();
        this.devicesNumber = devicesNumber;
        index = 0;
        for (int i = 0; i < devicesNumber; ++i) {
            devices.add(new Device());
        }
    }

    public Boolean isDeviceIdle() {
        for (Device device : devices) {
            if (device.getIsIdle()) {
                return true;
            }
        }
        return false;
    }

    public void put(Request request) {
        for (int i = 0; i < devicesNumber; ++i) {
            if (devices.get(index).getIsIdle()) {
                devices.get(index++).serviceRequest(request);
                if (index.equals(devicesNumber)) {
                    index = 0;
                }
                break;
            } else {
                index++;
                if (index.equals(devicesNumber)) {
                    index = 0;
                }
            }
        }
    }

    public void checkState(Double time) {
        for (Device device : devices) {
            device.checkTime(time);
        }
    }
}

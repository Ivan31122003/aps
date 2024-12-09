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

    public Device put(Request request) {
        Integer lastIndex = 0;
        for (int i = 0; i < devicesNumber; ++i) {
            if (devices.get(index).getIsIdle()) {
                lastIndex = index;
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
        return devices.get(lastIndex);
    }

    public void checkState(Double time) {
        for (Device device : devices) {
            device.checkTime(time);
        }
    }
}

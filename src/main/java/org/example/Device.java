package org.example;

public class Device {
    private static Integer idCounter = 1;
    private Integer id;

    public Device() {
        this.id = idCounter++;

    }
}

package org.example;

public class Device {
    private static Integer idCounter = 1;
    private Integer id;
    private Boolean isIdle = true;
    private Double releaseTime;

    public Device() {
        this.id = idCounter++;
    }

    public Boolean getIsIdle() {
        return isIdle;
    }

    public void serviceRequest(Request request) {
        isIdle = false;
        releaseTime = request.getTime() + request.getExecutingTime();
    }

    public void serviceBuffer(Request request) {
        isIdle = false;
        releaseTime += request.getExecutingTime();
    }

    public void checkTime(Double time) {
        if (!isIdle && time > releaseTime) {
            isIdle = true;
        }
    }
}

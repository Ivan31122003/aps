package org.example;

public class Device {
    private static Integer idCounter = 1;
    private Integer id;
    private Integer sourceId;
    private Integer requestId;
    private Boolean isIdle = true;
    private Double releaseTime;
    private Integer proccecedRequsets = 0;

    public Device() {
        this.id = idCounter++;
    }

    public Boolean getIsIdle() {
        return isIdle;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public Double getReleaseTime() {
        return releaseTime;
    }

    public void serviceRequest(Request request) {
        isIdle = false;
        sourceId = request.getSourceId();
        requestId = request.getId();
        releaseTime = (double) Math.round((request.getTime() + request.getExecutingTime()) * 10000.0) / 10000.0;
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

    public void printDevice() {
        isIdle = true;
        ++proccecedRequsets;
        System.out.println("Прибор: " + id);
        System.out.println("Заявка: " + sourceId + " " + requestId);
        System.out.println("Время: " + releaseTime);
        sourceId = null;
        requestId = null;
    }

    public Integer getProccecedRequsets() {
        return proccecedRequsets;
    }
}

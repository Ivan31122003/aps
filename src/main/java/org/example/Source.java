package org.example;

import java.util.ArrayList;
import java.util.List;

public class Source {
    private static Integer idCounter = 1;
    private final Integer id;
    private Integer requestId = 1;
    private List<Request> requests;
    private Integer requestsCounter = 0;
    private Integer rejectedRequestsCounter = 0;
    private Double currentTime;
    private Integer currentRequestId = null;

    public Source(Double delayForStart, Double delayTime, Integer requestsNumber, Double executingTime) {
        id = idCounter++;
        requests = new ArrayList<>();
        for(int i = 0; i < requestsNumber; ++i) {
             requests.add(new Request(id, requestId++, executingTime, delayForStart + delayTime * i, this));
        }
    }
    public Integer getId() {
        return id;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void incrementRequestCounter() {
        ++requestsCounter;
    }

    public void incrementRejectedRequestsCounter() {
        ++rejectedRequestsCounter;
    }

    public void setCurrentTime(Double time) {
        currentTime = time;
    }

    public Double getCurrentTime() {
        return currentTime;
    }

    public Integer getRequestsCounter() {
        return requestsCounter;
    }

    public Integer getRejectedRequestsCounter() {
        return rejectedRequestsCounter;
    }

    public void setCurrentRequestId(Integer currentRequestId) {
        this.currentRequestId = currentRequestId;
    }

    public Integer getCurrentRequesttId() {
        return currentRequestId;
    }
}

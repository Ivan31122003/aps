package org.example;

public class Request {
    private final Integer id;
    private final Integer sourceId;
    private final Double executingTime;
    private final Double time;

    public Request(Integer sourceId, Integer requestId, Double executingTime, Double time) {
        this.sourceId = sourceId;
        this.id = requestId;
        this.executingTime = executingTime;
        this.time = time;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public Integer getId() {
        return id;
    }

    public Double getExecutingTime() {
        return executingTime;
    }

    public Double getTime() { return time; }
}

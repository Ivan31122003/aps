package org.example;

public class Request {
    private final Integer id;
    private final Integer sourceId;
    private final Double executingTime;
    private Double time;
    private final Source source;

    public Request(Integer sourceId, Integer requestId, Double executingTime, Double time, Source source) {
        this.sourceId = sourceId;
        this.id = requestId;
        this.executingTime = executingTime;
        this.time = time;
        this.source = source;
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

    public Source getSource() {
        return source;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public void printRequest() {
        System.out.println(sourceId + "    " + id + "    " + time);
    }
}

package org.example;

public class Request {
    private static Integer idCounter = 1;
    private final Integer id;
    private final Integer sourceId;
    private final Integer executingTime;

    public Request(Integer sourceId) {
        this.id = idCounter++;
        this.sourceId = sourceId;
        this.executingTime = 3500;
    }

    public Integer getId() {
        return id;
    }

    public Integer sourceId() {
        return sourceId;
    }

    public Integer getExecutingTime() {
        return executingTime;
    }
}

package org.example;

public class Calendar {
    private final Long startTime;
    private List<MyEvent> events;

    public Calendar(Long startTime) {
        this.startTime = startTime;
    }

    public Long getStartTime() {
        return startTime;
    }
}

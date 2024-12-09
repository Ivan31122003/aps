package org.example;

public class Obj {
    private String name;
    private Double time;
    private Integer id;
    private Integer requestId;

    public Obj(String name, Double time, Integer id, Integer requestId) {
        this.name = name;
        this.time = time;
        this.id = id;
        this.requestId = requestId;
    }

    public String getName() {
        return name;
    }

    public Double getTime() {
        return time;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRequestId() {
        return requestId;
    }
}

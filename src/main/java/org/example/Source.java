package org.example;

import java.util.ArrayList;
import java.util.List;

public class Source {
    private 
    private final Integer delayForStart;
    private final Integer delayTime;
    private final Integer id;
    private Integer counter = 0;
    private List<Request> requests;

    public Source(Integer delayForStart, Integer delayTime, Integer numberOfRequests) {
        this.delayForStart = delayForStart;
        this.delayTime = delayTime;
        requests = new ArrayList<>();
        for (int i = 0; i < numberOfRequests; ++i) {
            requests.add(new Request(delayForStart + i * delayTime));
        }
    }
}

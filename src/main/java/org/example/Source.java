package org.example;

import java.util.ArrayList;
import java.util.List;

public class Source {
    private static Integer idCounter = 1;
    private final Integer id;
    private Integer requestId = 1;
    private List<Request> requests;

    public Source(Double delayForStart, Double delayTime, Integer requestsNumber, Double executingTime) {
        id = idCounter++;
        requests = new ArrayList<>();
        for(int i = 0; i < requestsNumber; ++i) {
             requests.add(new Request(id, requestId++, executingTime, delayForStart + delayTime * i));
        }
    }

    public List<Request> getRequests() {
        return requests;
    }
}

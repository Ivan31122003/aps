package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyProcess {
    private final Sources sources;
    private final Devices devices;
    private final Calendar calendar;
    private final Buffer buffer;
    private List<Request> requests;

    public MyProcess(Integer sourcesNumber, Integer bufferCapacity, Integer devicesNumber, Double sourcesInterval, Double sourcesStartDelay, Integer requestsNumber, Double requestTime) {
        this.sources = new Sources(sourcesNumber, sourcesStartDelay, sourcesInterval, requestsNumber, requestTime);
        requests = new ArrayList<>(sources.getRequestsList());
        Collections.sort(requests, new Comparator<Request>() {
            @Override
            public int compare(Request request1, Request request2) {
                return Double.compare(request1.getTime(), request2.getTime());
            }
        });
        this.buffer = new Buffer(bufferCapacity);
        this.devices = new Devices(devicesNumber);

        calendar = new Calendar();
    }

    public void start() {
        for (Request request : requests) {
            if (devices.isDeviceIdle()) {
                devices.put(request);
            } else {
                if (!buffer.put(request)) {

                }
            }
        }
    }
}

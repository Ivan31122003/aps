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
    private final Integer requestsNumber;
    private List<Request> requests;

    public MyProcess(Integer sourcesNumber, Integer bufferCapacity, Integer devicesNumber, Double sourcesStartDelay, Double sourcesInterval, Integer requestsNumber, Double requestTime) {
        this.requestsNumber = requestsNumber;
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
        for (int i = 0; i < requestsNumber; ++i) {
            if (calendar.isItem()) {
                Device device = calendar.getDevice();
                if (device.getReleaseTime() < requests.get(i).getTime()) {
                    calendar.incrementIndex();
                    device.printDevice();
//                    --i;
                    if (!buffer.getIsEmpty()) {
                        Request request = buffer.take();
                        request.setTime(device.getReleaseTime());
                        devices.put(request);
                        calendar.addItem(device);
                    }
                }
            }
//            } else {
                if (devices.isDeviceIdle()) {
                    Device device1 = devices.put(requests.get(i));
                    calendar.addItem(device1);
                    requests.get(i).printRequest();
                } else {
                    if (!buffer.put(requests.get(i))) {
                        System.out.println("Отказ " + requests.get(i).getSourceId() + "    " + requests.get(i).getId());
                    } else {
                        requests.get(i).printRequest();
                    }
                }
//            }
        }
        for (int i = calendar.getIndex(); i < calendar.getSize(); ++i) {
            Device device = calendar.getDevice();

            calendar.incrementIndex();
            device.printDevice();
//                    --i;
            if (!buffer.getIsEmpty()) {
                Request request = buffer.take();
                request.setTime(device.getReleaseTime());
                devices.put(request);
                calendar.addItem(device);
            }
        }
    }

    public void printInfoSources() {
        for (Request request : requests) {
            System.out.println(request.getSourceId() + "   " + request.getId() + "   " + request.getTime());
        }
    }
}

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
    private Boolean stepByStep = false;

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

    public void setStepByStep() {
        this.stepByStep = true;
    }

    public void start() {
        for (int i = 0; i < requestsNumber; ++i) {
            if(stepByStep) {
                try
                {
                    System.in.read();
                }
                catch(Exception e)
                {}
            }
            if (calendar.isItem() && calendar.getDevice().getReleaseTime() <= requests.get(i).getTime()) {
                Device device = calendar.getDevice();
                System.out.println("Освободился прибор:");
                calendar.incrementIndex();
                device.printDevice();
                sources.printSourcesState();
                devices.printDevices();
                buffer.printState();
                --i;
                if (!buffer.getIsEmpty()) {
                    Request request = buffer.take();
                    request.setTime(device.getReleaseTime());
                    devices.put(request);
                    calendar.addItem(device);
                }
            }
            else {
                if (devices.isDeviceIdle()) {
                    System.out.println("Поступила заявка:");
                    requests.get(i).printRequest(true, buffer.getIsEmpty());
                    sources.setSourceState(requests.get(i));
                    sources.printSourcesState();
                    devices.printDevices();
                    Device device1 = devices.put(requests.get(i));
                    calendar.addItem(device1);
                    buffer.printState();
                } else {
                    Boolean isExistsPlaceInBuffer = buffer.isExistsPlace();
                    if (!buffer.isExistsPlace()) {
                        System.out.println("Поступила заявка (отказ):");
                        requests.get(i).setIsRejected();
                        requests.get(i).printRequest(false, isExistsPlaceInBuffer);
                        sources.setSourceState(requests.get(i));
                        sources.printSourcesState();
                        devices.printDevices();
                        buffer.printState();
                    } else {
                        System.out.println("Поступила заявка:");
                        requests.get(i).printRequest(false, isExistsPlaceInBuffer);
                        sources.setSourceState(requests.get(i));
                        sources.printSourcesState();
                        devices.printDevices();
                        buffer.printState();
                        buffer.put(requests.get(i));
                    }
                }
            }
            System.out.println("---------------------------------------------------");
        }
        for (int i = calendar.getIndex(); i < calendar.getSize(); ++i) {
            if(stepByStep) {
                try
                {
                    System.in.read();
                }
                catch(Exception e)
                {}
            }
            Device device = calendar.getDevice();

            calendar.incrementIndex();
            System.out.println("Прибор освободился:");
            device.printDevice();
            sources.printSourcesState();
            devices.printDevices();
//                    --i;
            if (!buffer.getIsEmpty()) {
                Request request = buffer.take();
                request.setTime(device.getReleaseTime());
                devices.put(request);
                calendar.addItem(device);
            }
            System.out.println("---------------------------------------------------");
        }
    }

    public void printInfoSources() {
        for (Request request : requests) {
            System.out.println(request.getSourceId() + "   " + request.getId() + "   " + request.getTime());
        }
    }
}

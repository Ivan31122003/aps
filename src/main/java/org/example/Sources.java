package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sources {
    private List<Source> sources;
    private Integer requestsNumber;

    public Sources(Integer sourcesNumber, Double startDelay, Double interval, Integer requestsNumber, Double requestTime) {
        sources = new ArrayList<>();
        this.requestsNumber = requestsNumber;
        for (int i = 0; i < sourcesNumber; ++i) {
            sources.add(new Source(startDelay * (i + 1), interval, requestsNumber / sourcesNumber, requestTime));
        }
    }

    public List<Source> getSources() {
        return sources;
    }

    public List<Request> getRequestsList() {
        List<Request> requestsList = new ArrayList<>();
        for (Source source : sources) {
            requestsList.addAll(source.getRequests());
        }
        return requestsList;
    }

    public void setSourceState(Request request) {
        for (Source source : sources) {
            if (source.getId().equals(request.getSourceId())) {
                if(request.getIsRejected()) {
                    source.incrementRejectedRequestsCounter();
                }
                source.incrementRequestCounter();
                source.setCurrentRequestId(request.getId());
                source.setCurrentTime(request.getTime());
            }
        }
    }

    public void printSourcesState() {
        System.out.println("Источник   Время    Заявки   Отклоненные   Текущая");
        for (Source source : sources) {
            System.out.println("    " + source.getId() + "      " + (source.getCurrentTime() != null ? source.getCurrentTime() : "---") + "        " + source.getRequestsCounter() + "           " + source.getRejectedRequestsCounter() + "           " + (source.getCurrentRequesttId() != null ? source.getCurrentRequesttId() : "---"));
        }
    }

    public void printPercentageOfProcessedRequests() {
        Integer rejectedRequests = 0;
        for (Source source : sources) {
            rejectedRequests += source.getRejectedRequestsCounter();
        }
        double percent = Math.round((1d - (double) rejectedRequests / requestsNumber) * 10000d) / 100d;
        System.out.println("Процент обработанных заявок: " + percent + "%");
    }

    public void printAverageTimeOfRequestInSystem() {
        Integer requestsCounter = 0;
        Double aggRequestsTimeInSystem = 0d;
        for (Source source : sources) {
            for (Request request : source.getRequests()) {
                if (!request.getIsRejected()) {
                    ++requestsCounter;
                    aggRequestsTimeInSystem += request.getTimeInSystem();
                }
            }
        }
        Double averageTime = Math.round(aggRequestsTimeInSystem / requestsCounter * 1000d) / 1000d;
        System.out.println("Среднее время нахождения заявки в системе: " + averageTime);
    }
}

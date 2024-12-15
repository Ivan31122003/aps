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
}

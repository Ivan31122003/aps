package org.example;

import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private List<Request> requests;
    private Integer indexPut;
    private Integer indexTake;
    private Integer size;
    private Boolean isEmpty;
    private final Integer capacity;

    public Buffer(Integer capacity) {
        requests = new ArrayList<>();
        for (int i = 0; i < capacity; ++i) {
            requests.add(new Request(1, 1, 1d, 1d));
        }
        indexPut = 0;
        indexTake = 0;
        size = 0;
        isEmpty = true;
        this.capacity = capacity;
    }

    public Boolean getIsEmpty() {
        return isEmpty;
    }

    public Boolean put(Request request) {
        if (size.equals(capacity)) {
            return false;
        }
        size++;
        requests.set(indexPut++, request);
        if (indexPut.equals(capacity)) {
            indexPut = 0;
        }
        isEmpty = false;
        return true;
    }

    public Request take() {
        Request request = requests.get(indexTake);
        size--;
        indexTake++;
        if(indexTake.equals(capacity)) {
            indexTake = 0;
        }
        if (size.equals(0)) {
            isEmpty = true;
        }
        return request;
    }
}

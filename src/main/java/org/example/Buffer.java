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
            requests.add(new Request(1, 1, 1d, 1d, new Source(1d, 1d, 1, 1d)));
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
        ++size;
        requests.set(indexPut % capacity, request);
        ++indexPut;
//        if (indexPut.equals(capacity)) {
//            indexPut = 0;
//        }
        isEmpty = false;
        return true;
    }

    public Request take() {
        Request request = requests.get(indexTake % capacity);
        --size;
        ++indexTake;
//        if(indexTake.equals(capacity)) {
//            indexTake = 0;
//        }
        if (size.equals(0)) {
            isEmpty = true;
        }
        return request;
    }

    public Boolean isExistsPlace() {
        return size < capacity;
    }

    public void printState() {
        System.out.println("Буфер:\nЯчейка    Источник    Заявка");
        for (int i = 0, q = indexTake; i < capacity; ++i) {
            System.out.print("   " + (i + 1));
            if (size.equals(capacity) || (q < indexPut && q % capacity == i)) {
                System.out.println("         " + requests.get(q % capacity).getSourceId() + "          " + requests.get(q % capacity).getId());
                ++q;
            } else {
                System.out.println("        ---        ---");
            }
        }
    }
}

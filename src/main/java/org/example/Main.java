package org.example;


public class Main {
    public static void main(String[] args) {
        Sources sources = new Sources(2, 1.0, 3.5, 20, 3.5);

        for (Request request : list) {
            System.out.println(request.getSourceId() + "   " + request.getId() + "   " + request.getTime());
        }
    }
}
package org.example;


public class Main {
    public static void main(String[] args) {
        MyProcess process = new MyProcess(2, 2, 2, 1.0, 3.5, 12, 7.0);
//        process.printInfoSources();
        process.start();
    }
}
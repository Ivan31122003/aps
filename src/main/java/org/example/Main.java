package org.example;

public class Main {
    public static void main(String[] args) {
        MyProcess process = new MyProcess(2, 2, 2, 0.1, 3.5, 30, 5.5);
        process.setStepByStep();
        process.start();
    }
}
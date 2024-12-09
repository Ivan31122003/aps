package org.example;

public class Main {
    public static void main(String[] args) {
        Calendar calendar = new Calendar(System.currentTimeMillis());
        System.out.println(calendar.getStartTime());
    }
}
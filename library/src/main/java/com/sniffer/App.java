package com.sniffer;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        producer.start();
    }
}


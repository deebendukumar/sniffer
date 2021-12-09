package com.sniffer;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
		/*
		 * Consumer consumer1 = new Consumer(); Consumer consumer2 = new Consumer();
		 * Consumer consumer3 = new Consumer(); Consumer consumer4 = new Consumer();
		 */
        producer.start();
		/*
		 * consumer1.start(); consumer2.start(); consumer3.start(); consumer4.start();
		 */
    }
}


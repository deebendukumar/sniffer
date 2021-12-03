package com.sniffer;

import com.sniffer.throttling.Handler;
import com.sniffer.throttling.Tenant;
import com.sniffer.throttling.ThrottleImpl;

public class App {

    public static void main(String[] args) throws InterruptedException {

        Producer producer = new Producer();
        Consumer consumer01 = new Consumer();
        Consumer consumer02 = new Consumer();
        Consumer consumer03 = new Consumer();
        Consumer consumer04 = new Consumer();

        producer.start();
        consumer01.start();
        consumer02.start();
        consumer03.start();
        consumer04.start();
    }
}

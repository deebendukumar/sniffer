package com.sniffer;

import com.sniffer.throttling.Handler;
import com.sniffer.throttling.Tenant;
import com.sniffer.throttling.ThrottleImpl;

public class App {

    public static void main(String[] args) throws InterruptedException {

        final Integer throughput = 256;

        Handler handler = new Handler();
        Tenant tenant = new Tenant("Producer", throughput, handler);

        ThrottleImpl timer = new ThrottleImpl(throughput, tenant, handler);
        timer.start();

        while(true) {
            Thread.sleep(1000);
        }

        /**
         * create a producer and insert data @1000 messages per second
         */

        /**
         * create 4 consumers to read data @250 messages per second
         */

//        Consumer consumer01 = new Consumer();
//        Consumer consumer02 = new Consumer();
//        Consumer consumer03 = new Consumer();
//        Consumer consumer04 = new Consumer();
//
//        producer.start();
//        consumer01.start();
//        consumer02.start();
//        consumer03.start();
//        consumer04.start();
    }
}

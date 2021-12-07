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
        
        Producer producer = new Producer();
        producer.start();
//        producer.join();

        /**
         * main should wait for its child process to terminate
         */
    }

}


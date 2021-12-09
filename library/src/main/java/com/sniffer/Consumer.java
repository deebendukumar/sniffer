package com.sniffer;

import com.sniffer.throttling.Handler;
import com.sniffer.throttling.Tenant;
import com.sniffer.throttling.ThrottleImpl;
import com.sniffer.utils.ConcurrentSynchronizeQueue;
import com.sniffer.utils.ProcessingThread;

public class Consumer extends ProcessingThread {

    private static Integer instance = 0;

    private final Integer THROUGHPUT = 25;

    private ConcurrentSynchronizeQueue queue = null;
    private Handler handler = null;
    private Tenant tenant = null;
    private ThrottleImpl timer = null;

    public Consumer() {
        queue = ConcurrentSynchronizeQueue.getInstance();
        handler = new Handler();
        tenant = new Tenant("Consumer" + ++instance, THROUGHPUT, handler);
        timer = new ThrottleImpl(THROUGHPUT, tenant, handler);
        timer.start();
    }

    @Override
    public void process() {
        try {
            if (handler.getCount(tenant.getName()) < THROUGHPUT && !queue.isEmpty()) {
                Object obj = queue.dequeue();
              //  System.err.println(obj.toString());
                handler.incrementCount(tenant.getName());
            } else {
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

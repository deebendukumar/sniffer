package com.sniffer;

import com.sniffer.throttling.Handler;
import com.sniffer.throttling.Tenant;
import com.sniffer.throttling.ThrottleImpl;
import com.sniffer.utils.ConcurrentSynchronizeQueue;
import com.sniffer.utils.HexUtil;
import com.sniffer.utils.ProcessingThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer extends ProcessingThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private static Integer instance = 0;
    private final Integer THROUGHPUT = 1000;

    private ConcurrentSynchronizeQueue queue = null;
    private Handler handler = null;
    private Tenant tenant = null;
    private ThrottleImpl timer = null;

    public Producer() {
        queue = ConcurrentSynchronizeQueue.getInstance();
        handler = new Handler();
        tenant = new Tenant("Producer" + ++instance, THROUGHPUT, handler);
        timer = new ThrottleImpl(THROUGHPUT, tenant, handler);
        timer.start();
    }


    @Override
    public void process() {
        String s = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
        try {
            if (handler.getCount(tenant.getName()) < THROUGHPUT) {
                queue.enqueue(HexUtil.toHexString(s.getBytes()));
                handler.incrementCount(tenant.getName());
            } else {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

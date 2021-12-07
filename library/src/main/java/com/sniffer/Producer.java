package com.sniffer;

import com.sniffer.throttling.Handler;
import com.sniffer.throttling.Tenant;
import com.sniffer.throttling.ThrottleImpl;
import com.sniffer.utils.ConcurrentSynchronizeQueue;
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
        tenant = new Tenant("Producer" + ++instance , THROUGHPUT, handler);
        timer = new ThrottleImpl(THROUGHPUT, tenant, handler);
        timer.start();
    }

    @Override
    public void process() {
      
		/**
		 * produce
		*/    	
    	 String s="helloAddStringMehelloAddStringMehelloAddStringMehelloAddStringMe";
		 
 		for(int i=0;i<=THROUGHPUT;i++) {
 			try {
				queue.enqueue(s);
			} catch (IndexOutOfBoundsException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			System.err.println("add");
 			
 		}
 		 try {
			System.err.println(queue.size() +"insert");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        handler.incrementCount(tenant.getName());
        if (handler.getCount(tenant.getName()) >= THROUGHPUT) {
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

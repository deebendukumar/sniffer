package com.sniffer;

import com.sniffer.throttling.Handler;
import com.sniffer.throttling.Tenant;
import com.sniffer.throttling.ThrottleImpl;
import com.sniffer.utils.ConcurrentSynchronizeQueue;
import com.sniffer.utils.ProcessingThread;

public class Consumer extends ProcessingThread {

	private static Integer instance = 0;

	private final Integer THROUGHPUT = 100;

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
				long startTime = System.currentTimeMillis();
				System.err.println("consumer process ");
				Object obj = queue.dequeue();
				handler.incrementCount(tenant.getName());
				long endTime = System.currentTimeMillis();
				long diff = endTime =startTime;
                   
				long l = 10L;

				if (diff <= (1000 / THROUGHPUT)) {
					System.err.println("consumer process if 1 ");
					l = (1000 / THROUGHPUT) - (endTime - startTime);
					
				}
					
					
				 else {
					l = 2L;
				}
				Thread.sleep(l);
			} else {
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

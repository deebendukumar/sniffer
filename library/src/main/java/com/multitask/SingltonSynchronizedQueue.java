package com.multitask;



import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;





public class SingltonSynchronizedQueue {
	
	
	public static final int RESIDENT_QUEUE_SIZE = 5;
	private static final Object object = new Object();
	static Queue<String> queue = new ConcurrentLinkedQueue<String>();
	private static volatile SingltonSynchronizedQueue instance = null;
	private AtomicBoolean atLocked = new AtomicBoolean(false);
	
 
	private SingltonSynchronizedQueue() {
	}

	public static SingltonSynchronizedQueue getInstance() {
		if (instance != null) {
			return instance;
		}

		synchronized (object) {
			if (instance == null) {
				instance = new SingltonSynchronizedQueue();
			}

			return instance;
		}
	}

	// Inserts the specified element into this queue if it is possible to do so

	public  void addData(String value) throws InterruptedException {
		synchronized (queue) {
			if(queue.size()<RESIDENT_QUEUE_SIZE) {
			queue.add(value);
			
			
		
			}	
		}
	
	}


	public static String pollData() {
		String data = queue.poll();
		return data;
		
	}
	
	public static int size() {
		int data = queue.size();
		return data;
	}
	public void ReadData(ConcurrentLinkedQueue<String> queue) {
		
		
	}
}

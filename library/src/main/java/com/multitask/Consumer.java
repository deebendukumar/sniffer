package com.multitask;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Consumer implements Runnable{

   // protected Queue<String> queue = null;
    private static SingltonSynchronizedQueue singltonSynchronizedQueue=SingltonSynchronizedQueue.getInstance();
  
    public void run() {
    	singltonSynchronizedQueue.pollData();
        System.err.println(singltonSynchronizedQueue.size()+"read");
        
    }
}
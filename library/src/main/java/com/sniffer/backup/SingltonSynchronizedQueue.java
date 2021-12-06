package com.sniffer.backup;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;


import org.pcap4j.packet.Packet;


public class SingltonSynchronizedQueue {

	private static final Object object = new Object();
	static Queue<Packet> queue = new ConcurrentLinkedQueue<Packet>();
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

	public static void addData(Packet value) {
		synchronized (queue) {
			queue.add(value);
		}
	}

	// Retrieves and removes the head of this queue, or returns null if this
	// queue is empty.
	public static Packet pollData() {
		Packet data = queue.poll();
		return data;
	}
}

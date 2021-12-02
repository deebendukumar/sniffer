package com.sniffer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapStat;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.Packet;

import org.pcap4j.util.NifSelector;

/*
 * Author Yatinder Pal Singh
 * Packets  Receiver
*/
public class NICPackets {
	private static final String COUNT_KEY = NICPackets.class.getName() + ".count";
	private static final int COUNT = Integer.getInteger(COUNT_KEY, 1);

	private static final String READ_TIMEOUT_KEY = NICPackets.class.getName() + ".readTimeout";
	private static final int READ_TIMEOUT = Integer.getInteger(READ_TIMEOUT_KEY, 10); // [ms]

	private static final String SNAPLEN_KEY = NICPackets.class.getName() + ".snaplen";
	private static final int SNAPLEN = Integer.getInteger(SNAPLEN_KEY, 65536);
	static StringBuilder sb;
	private SingltonSynchronizedQueue singltonSynchronizedQueue=SingltonSynchronizedQueue.getInstance();
	public static void main(String[] args) throws PcapNativeException {
		
      
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();

			System.out.println("The mac Address of this machine is :" + ip.getHostAddress());

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();

			System.out.print("The mac address is : ");

			sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}

			System.out.println(sb.toString());
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}

		


		PcapNetworkInterface nif;
		try {
			nif = new NifSelector().selectNetworkInterface();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		if (nif == null) {
			return;
		}

		System.out.println(nif.getName() + "(" + nif.getDescription() + ")" + "  " + nif.getAddresses());

		PcapHandle handle = nif.openLive(SNAPLEN, PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);
		PacketListener listener = new PacketListener() {
			public void gotPacket(Packet packet) {

				try {
					
					SingltonSynchronizedQueue.addData(packet);
				  Packet p=	SingltonSynchronizedQueue.pollData();
				  printPacket(p);
				} catch (PcapNativeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotOpenException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		try {
			handle.loop(5, listener);
		} catch (PcapNativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotOpenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
       
	
private static void printPacket(Packet packet, PcapHandle ph) throws PcapNativeException, NotOpenException {
		
	    System.out.println(Thread.currentThread().getName());
		PcapStat ps = ph.getStats();
		System.out.println("Packets Received: " + ps.getNumPacketsReceived());
		StringBuilder sb = new StringBuilder();
		sb.append("A packet captured at ").append(ph.getTimestamp()).append(":");
		System.out.println(sb);
		System.out.println(packet);
		StringBuilder s1 = new StringBuilder();
		s1.append(packet);
		String s = s1.toString();
		System.err.println(s);

		
	}
private static void printPacket(Packet packet) throws PcapNativeException, NotOpenException {
	
    System.out.println(Thread.currentThread().getName());
	
	//System.out.println("Packets Received: " + ps.getNumPacketsReceived());
	StringBuilder sb = new StringBuilder();
	sb.append("A packet captured at ").append(":");
	System.out.println(sb);
	System.out.println(packet);
	StringBuilder s1 = new StringBuilder();
	s1.append(packet);
	String s = s1.toString();
	System.err.println(s);

	
}

}

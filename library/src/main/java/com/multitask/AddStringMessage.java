package com.multitask;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

public class AddStringMessage {
	
	public static final int RESIDENT_QUEUE_SIZE = 999999999;
	  public static final int ADD_AND_REMOVES = 1_000_000;

	  
	  
	private static SingltonSynchronizedQueue singltonSynchronizedQueue=SingltonSynchronizedQueue.getInstance();
	public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
		// TODO Auto-generated method stub
		

		  
		   AddStringMessage obj = new AddStringMessage();
		      long start1 = System.nanoTime();
		      long end1 = System.nanoTime();   
		      long s=end1-start1;
		      System.out.println("Elapsed Time in nano seconds: "+ (end1-start1));   
		      double elapsedTimeInSecond = (double) s / 1_000_000_000;
		      System.out.println("Elapsed Time in nano seconds: "+elapsedTimeInSecond+" "); 
		      
		      obj.test();
		      System.err.println(singltonSynchronizedQueue.size() +"insert");
		     
		   try {
			   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}     
	
	public void test() throws InterruptedException{
		 String s="helloAddStringMehelloAddStringMehelloAddStringMehelloAddStringMe";
		 
		for(int i=0;i<=RESIDENT_QUEUE_SIZE;i++) {
			singltonSynchronizedQueue.addData(s);
			System.err.println("add");
		}
		
		
		
	   }
	
	

	  
}
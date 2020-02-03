package se.lexicon.anton.ThreadSafeSingletonExamples.models;

import java.util.Arrays;

/**
 * @author Pankaj
 * @author Anton
 *
 */

public class SyncronizedMethod {

	public static void main(String[] args) throws InterruptedException{
		String[] arr = {"1", "2", "3", "4", "5", "6"};
		long start = System.currentTimeMillis();
		HashMapProcessor hmp = new HashMapProcessor(arr);
		//Create and Start all threads
		Thread t1 = new Thread(hmp, "t1");
		t1.start();
		Thread t2 = new Thread(hmp, "t2");
		t2.start();
		Thread t3 = new Thread(hmp, "t3");
		t3.start();
		
		//wait for all threads to finish, then join
		t1.join();
		t2.join(); 
		t3.join();
		
		//print time
		System.out.println("Time taken = " + (System.currentTimeMillis()-start));
		//check the shared variable value now
		System.out.println(Arrays.asList(hmp.getMap()));
	}
}

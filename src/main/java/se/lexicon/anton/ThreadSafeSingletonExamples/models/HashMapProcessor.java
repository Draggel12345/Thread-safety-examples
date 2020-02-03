package se.lexicon.anton.ThreadSafeSingletonExamples.models;

/**
 * @author Pankaj
 * @author Anton
 *
 */

public class HashMapProcessor implements Runnable {

	private String[] strArr = null;
	
	public HashMapProcessor(String[] m) {
		this.strArr=m;
	}
	
	public String[] getMap() {
		return strArr;
	}
	
	public void run() {
		processArr(Thread.currentThread().getName());
	}
	
	private void processArr(String name) {
		for (int i = 0; i < strArr.length; i++) {
			//process data and append thread name
			processSomething(i);
			addThreadName(i, name);
		}
	}
	
	//The String array values are corrupted because of shared data and 
	//no synchronization.
	private Object lock = new Object();
	private void addThreadName(int i, String name) {
		//By adding Object lock and use synchronized, we make our
		//program thread-safe.
		synchronized (lock) {
			strArr[i] = strArr[i] + ":" + name;	
		}
	}
	
	private void processSomething(int index) {
		//processing some job
		try {
			Thread.sleep(index*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}

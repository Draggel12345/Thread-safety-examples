package se.lexicon.anton.ThreadSafeSingletonExamples.models;

/**
 * @author Pankaj
 * @author Anton 
 *
 */
public class ProcessingThread implements Runnable{

	private int count;
	
	//dummy object variable for synchronization
	private Object mutex = new Object();
	
	public void run() {
		for (int i = 1; i < 5; i++) {
			processSomething(i);
			//by adding dummy Object and synchronized to our count++ variable 
			//the program becomes thread-safe
			synchronized (mutex) {
				count++;
			}
		}
	}
	
	public int getCount() {
		return this.count;
	}
	
	private void processSomething(int i) {
		//processing some job
		try {
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

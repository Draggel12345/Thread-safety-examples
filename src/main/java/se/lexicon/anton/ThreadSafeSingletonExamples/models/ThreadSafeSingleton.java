package se.lexicon.anton.ThreadSafeSingletonExamples.models;

/**
 * User synchronized block inside the if loop and volatile variable.
 * Pros: 
 * 1. Thread safety is guaranteed.
 * 2. Client application can pass arguments.
 * 3. Lazy initialization achieved.
 * 4. Synchronization overhead is minimal and applicable only
 *    for first few threads when the variable is null.
 * Cons:
 * 1. Extra if condition.
 * 
 * @author Anton
 * @author Pankaj
 *
 */

public class ThreadSafeSingleton {
	
	private static volatile ThreadSafeSingleton instance;
	private static Object mutex = new Object();
	
	private ThreadSafeSingleton() {}
	
	public static ThreadSafeSingleton getInstance() {
		ThreadSafeSingleton result = instance;
		if(result == null) {
			synchronized (mutex) {
				result = instance;
				if(result == null)
					instance = result = new ThreadSafeSingleton();
			}
		}
		return result;
	}
}

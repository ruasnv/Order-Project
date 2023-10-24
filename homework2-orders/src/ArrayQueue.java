
public final class ArrayQueue <T> implements QueueInterface<T> {
//	***first in first out***
	private T[] queue; //circular array of queue entries and one unused location
	private int frontIndex;
	private int backIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 1000;
	
	
//	constructors
	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayQueue(int initialCapacity) {
		
		if (initialCapacity < DEFAULT_CAPACITY) {
			initialCapacity = DEFAULT_CAPACITY;
		}
		else {
			checkCapacity(initialCapacity);
		}
		
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1]; 
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
		initialized = true;
	}
	
//	adds a new entry to the back of the queue
	public void enqueue(T newEntry) {
		checkInitialization();
		
		ensureCapacity();
		
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
	}
	
//	removes and returns the entry at the front of the queue
	public T dequeue() {
		checkInitialization();
		
		if (isEmpty()) {
			return null;
		}
		else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			return front;
		}
	}

	
//	retrieves the queue's front entry without changing the queue in any way
	public T getFront() {
		checkInitialization();
		
		if (isEmpty()) {
			return null;
		}
		else {
			return queue[frontIndex];
		}
	}
	
	
	
//	detects whether the queue is empty
	public boolean isEmpty() {
		return frontIndex == (backIndex + 1) % queue.length;
	}
	
//	removes all entries from the queue
	public void clear() {
		 checkInitialization();
		 
		 if (!isEmpty()) {
			 for (int index = frontIndex; index != backIndex;
				  index = (index + 1) % queue.length) {
				 queue[index] = null;
			 } 
			 queue[backIndex] = null;
		 } 
		 frontIndex = 0;
		 backIndex = queue.length - 1;
	}

//	throws an exception if this object is not initialized
	private void checkInitialization() {
		if(!initialized) {
			throw new SecurityException("ArrayQueue object is not initialized properly.");
		}
	}
	
//	throws an exception if the client requests a capacity that is too large
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a queue whose"
											+ "capacity exeeds allowed "
											+ "maximum of " + MAX_CAPACITY);
		}
	}
	
//	doubles the size of the array queue if it is full.
	private void ensureCapacity() {
		if (frontIndex == ((backIndex + 2) % queue.length)) { 
			// If array is full, double size of array
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;
			int newSize = 2 * oldSize;
			checkCapacity(newSize - 1);
			
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[newSize];
			queue = tempQueue;
			for (int index = 0; index < oldSize - 1; index++) {
				queue[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			} 
			frontIndex = 0;
			backIndex = oldSize - 2;
		} 
	}

}
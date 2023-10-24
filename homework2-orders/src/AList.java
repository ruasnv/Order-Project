
public final class AList <T> implements ListInterface<T>{
	private T[] list;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int MAX_CAPACITY = 1000;
	private static final int DEFAULT_CAPACITY = 30;

//	constructors
	public AList() {
		this(DEFAULT_CAPACITY);
	}
	
	public AList(int initialCapacity) {
		
		if (initialCapacity < DEFAULT_CAPACITY) {
			initialCapacity = DEFAULT_CAPACITY;
		}
		else {
			checkCapacity(initialCapacity);
		}
		
		@SuppressWarnings("unchecked")
		T[] tempList = (T[])new Object[initialCapacity];
		
		list = tempList;
		numberOfEntries = 0;
		initialized = true;
	}
	
	
//	adds newEntry to the end of the list
	public void add(T newEntry) {
		checkInitialization();
		
		list[numberOfEntries] = newEntry;
		numberOfEntries++;
		
		ensureCapacity();
	}
	
	
//	adds newEntry at position newPosition 
//	within the list position 1 indicates the first entry in the list
	public void add(int newPosition, T newEntry) {
		checkInitialization();
		
		if ((newPosition >= 0) && (newPosition < numberOfEntries)) {
			makeRoom(newPosition);
		
		list[newPosition] = newEntry;
		numberOfEntries++;
		ensureCapacity();
		}
		else {
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
		}
	}
	
	
//	removes and returns the entry at position givenPosition
	public T remove(int givenPosition) {
		checkInitialization();
		
		if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
			assert !isEmpty();
			T result = list[givenPosition];
			
			if (givenPosition < numberOfEntries) {
				removeGap(givenPosition);
			}
			
			numberOfEntries--;
			return result;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
		}
	}
	

//	replaces the entry at position givenPosition with newEntry;
	public T replace(int givenPosition, T newEntry) {
		checkInitialization();
		
		if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
			assert !isEmpty();
			T originalEntry = list[givenPosition];
			list[givenPosition] = newEntry;
			return originalEntry;
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
		}
	}
	
	
//	retrieves the entry at position givenPosition
	public T getEntry(int givenPosition) {
		checkInitialization();
		
		if ((givenPosition >= 0) && (givenPosition < numberOfEntries)) {
			assert !isEmpty();
			return list[givenPosition];
		}
		else {
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation."); 
		}
	}

	
//	retrieves all entries that are in the list in the order in which they occur
	public T[] toArray() {
		checkInitialization();
		
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		
		for(int index = 0; index < numberOfEntries; index++) {
			result[index] = list[index];
		}
		
		return result;
	}
	
	
//	sees whether the list contains anEntry
	public boolean contains(T anEntry) {
		checkInitialization();
		
		boolean found = false;
		int index = 0;
		
		while (!found && (index < numberOfEntries)) {
			
			if (anEntry.equals(list[index])) {
				found = true;
			}
			
			index++;
		}
		
		return found;
	}
	
	
//	gets the number of entries currently in the list
	public int getLength() {
		return numberOfEntries;		
	}
	
	
//	sees whether the list is empty
	public boolean isEmpty() {		
		return numberOfEntries == 0;
	}
	
	
//	removes all entries from the list
	public void clear() {	
		numberOfEntries = 0;
		list = null;
	}
	
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity() {
		T[] tempList = null;
		int capacity = list.length - 1;
		
		if (numberOfEntries >= capacity) {
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity);
			tempList = (T []) new Object[newCapacity];
			
			for (int index = 0; index < capacity; index++) {
				tempList[index] = list[index];
			}
			
			list = tempList;
		}
				
	}

	private void checkInitialization() {		
		if(!initialized) {
			throw new SecurityException("AList object is not initialized properly.");
		}
	}

//	throws an exception if the client requests a capacity that is too large
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a list whose"
											+ "capacity exeeds allowed "
											+ "maximum of " + MAX_CAPACITY);
		}
	}
	
	private void makeRoom(int newPosition) {
		assert (newPosition >= 0) && (newPosition <= numberOfEntries);
		
		int newIndex = newPosition;
		int lastIndex = numberOfEntries;
		
		for (int index = lastIndex; index >= newIndex; index--) {
			list[index + 1] = list[index];
		}
	}
	
	
	private void removeGap(int givenPosition) {
		assert (givenPosition >= 0) && (givenPosition < numberOfEntries);
		
		int removedIndex = givenPosition;
		int lastIndex = numberOfEntries;
		
		for (int index = removedIndex; index < lastIndex; index++) {
			list[index] = list[index + 1];
		}
		
	}
}

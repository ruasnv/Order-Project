import java.util.EmptyStackException;

public final class ArrayStack <T> implements StackInterface <T>{
//	***last in first out***
	
	private T[] stack;
	private int top;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 1000;
	
	
//	constructors
	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayStack(int initialCapacity) {
		checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[initialCapacity]; 
		stack = tempStack;
		top = -1;
		initialized = true;
	}
	
	
//	adds a new entry to the top of the stack
	public void push(T newEntry) {
		checkInitialization();
		ensureCapacity();
		
		stack[top + 1] = newEntry;
		top++;
	}
	
	
//	removes and returns the stack's top entry
	public T pop() {
		checkInitialization();
		
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			T topItem = stack[top];
			stack[top] = null;
			top--;
			return topItem;
		}
	}
	
//	retrieves the stack's top entry 
//  without changing the stack in any way
	public T peek() {
		checkInitialization();
		
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			return stack[top];
		}
	}
	
//	detects whether the stack is empty
	public boolean isEmpty() {
		return top < 0;
	}
	
//	detects whether the stack is full
	public boolean isFull() {
		return top == stack.length - 1;
	}
	
	
//	removes all entries from the stack
	public void clear() {
		while (top > -1) {
			stack[top] = null;
			top--;
		}
	}
		
//	if array is full, double its size
	@SuppressWarnings("unchecked")
	private void ensureCapacity() {
		T[] tempStack = null;
		
		if (isFull()) { 
			int newSize = 2 * stack.length;
			tempStack = (T[]) new Object[newSize];
		
			for (int i = 0; i < stack.length; i++) {
				tempStack[i] = stack[i];
			}
			
			stack = tempStack;		
		}
	}
	
//	printing the contents of the piles
	public void print() {
		
		while (!isEmpty()) {
			T nextItem = pop();
			System.out.println(((Product) nextItem).getName().toUpperCase() + " " + ((Product) nextItem).getExpirationDate());
		}
	}
	
	
//	throws an exception if this object is not initialized
	private void checkInitialization() {
		if(!initialized) {
			throw new SecurityException("ArrayStack object is not initialized properly.");
		}
	}
	
//	throws an exception if the client requests a capacity that is too large
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a stack whose"
											+ "capacity exeeds allowed "
											+ "maximum of " + MAX_CAPACITY);
		}
	}
}

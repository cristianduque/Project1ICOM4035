package classes;


public class SLLStack<E> implements Stack<E>
{
	private Node<E> first, last; //first 
	private int size; 
	
	public SLLStack() {
		first = last = null; 
		size = 0; 
	}
	
	public void addFirst(E e) { // adds element e to the front of the list
		first = new Node<>(e, first); // create and link a new node
		if (size == 0)
			last = first; 
		size++;
	}
	
	public E removeFirst() { // removes and returns the first element
		if(isEmpty()) return null; // nothing to remove
			E answer = first.getElement();
		first = first.getNext(); // will become null if list had only one node
		size--;
		if (size == 0)
			last = null; // special case as list is now empty
		return answer;
	}
	
	public E pop() throws EmptyStackException {
		return this.removeFirst();
	}

	public void push(E e) {
		this.addFirst(e);
	}

	public E top() throws EmptyStackException {
		return this.first();
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public E first( ) { // returns (but does not remove) the first element
		if (isEmpty( )) return null;
		return first.getElement( );
	}
	
	public void addLast(E e) { // adds element e to the end of the list
		 Node<E> newest = new Node<>(e, null); // node will eventually be the tail
		 if (isEmpty( ))
			 first = newest; // special case: previously empty list
		 else
			 last.setNext(newest); // new node after existing tail
		 last = newest; // new node becomes the tail
		 size++;
	}
	
	public E last( ) { // returns (but does not remove) the last element
		 if (isEmpty( )) return null;
		 return last.getElement( );
	 }
}



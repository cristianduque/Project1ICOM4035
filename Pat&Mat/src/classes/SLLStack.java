package classes;


public class SLLStack<E> implements Stack<E>
{
	private Node<E> top; //first 
	private int size; 
	
	public SLLStack() {
		top = null; 
		size = 0; 
	}
	
	public E pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("pop: Stack is empty."); 
		E etr = top.getElement(); 
		Node<E> newTop = top.getNext();
		top.clean();		
		top = newTop;
		size--;
		return etr;
	}

	public void push(E e) {
		Node<E> newTop = new Node<E>(e,top);
		top=newTop;
		
		size++; 
	}

	public E top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException("pop: Stack is empty."); 
		return top.getElement();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}


	// just for testing and grading....
    public void showReverse() { 
	    if (size == 0)
		   System.out.println("Stack is empty."); 
		else
		   recSR(top);
    } 
    private void recSR(Node<E> f) { 
		if (f != null) { 
		   recSR(f.getNext()); 
		   System.out.println(f.getElement()); 
	     } 
    } 
 
}



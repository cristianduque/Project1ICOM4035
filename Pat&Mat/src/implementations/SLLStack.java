package implementations;

import exceptions.EmptyStackException;



/* This class serves as a implementation for the abstract data 
 * type Stack using singly linked list with references to the first
 * node, that is to be used within the Last-Come-First-Served
 * method (LCFS) that Mat's want to approach the problem.
 */
import interfaces.Stack;

public class SLLStack<E> implements Stack<E>
{
	private Node<E> front; //top of the stack
	private int size;  //number of elements within the stack
	
	public SLLStack() {
		front = null; 
		size = 0; 
	}
	/* Removes and return the front element of the stack. 
	 * @return null if the stack is empty
	 * @return the front element of the stack
	 */
	
	public E pop() throws EmptyStackException {
		if(isEmpty()) return null; // nothing to remove
		E answer = front.getElement();
		front = front.getNext(); // will become null if list had only one node
		size--;
		return answer;
	}
	
	
	/* Adds element e to the front of the stack
	 * @param e the element to be added to the front of the list
	 */

	public void push(E e) {
		front = new Node<>(e, front); // create and link a new node
		size++;
	}
	
	/* Same method as pop but it does not removes the front of the stack,
	 * it only returns the element of the front of the stack
	 * @return the front element of the stack
	 */

	public E top() throws EmptyStackException {
		return front.getElement();
	}
	
	/* Returns the number of elements in the stack
	 * @return the number of elements in the stack
	 */
	
	public int size() {
		return size;
	}
	
	/* Returns the boolean value whether the stack is empty or not
	 * @return true if the stack is empty
	 * @return if the stack is not empty
	 */

	public boolean isEmpty() {
		return size == 0;
	}
}



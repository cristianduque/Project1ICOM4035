package implementations;

import interfaces.Queue;

/** 
   This class is a implementation of the Queue using a singly linked list 
   with references to the first and to the last node, that is to be used 
   within the First-Come-First-Served (FCFS), Shortest-Job-First, and
   Max Profit methods that Pat, Max, and Pac want to approach.
**/
public class SLLQueue<E> implements Queue<E> {

	private Node<E> first, last;
	private int size; 
	
	public SLLQueue() {           // initializes instance as empty queue
		first = null; 
		size = 0; 
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public E first() {
		if (isEmpty()) return null;
		return first.getElement(); 
	}
	
	public Node<E> firstNode(){
		if(isEmpty()) return null;
		return first;
	}
	
	public E last(){
		if(isEmpty()) return null;
		return last.getElement();
	}
	public E dequeue() {
		if (isEmpty())
			return null;
		E etr = first.getElement(); 
		Node<E> newFirst = first.getNext();
		first.clean();
		first = newFirst;
		size--;
		return etr;
	}
	public void enqueue(E e) {
		Node<E> nn = new Node<E>(e); 
		if (size == 0)
			first = nn; 
		else 
			last.setNext(nn); 
		last = nn; 
		size++; 
	}

}

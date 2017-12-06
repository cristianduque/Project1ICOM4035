package classes;

public class Node<E> {
	private E element; 
	private Node<E> next; 
	
	public Node() { 
		element = null; 
		next = null; 
	}
	public Node(E data, Node<E> next) { 
		this.element = data; 
		this.next = next; 
	}
	public Node(E data)  { 
		this.element = data; 
		next = null; 
	}
	public E getElement() {
		return element;
	}
	public void setElement(E data) {
		this.element = data;
	}
	public Node<E> getNext() {
		return next;
	}
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	public void clean() { 
		element = null; 
		next = null; 
	}
	
}

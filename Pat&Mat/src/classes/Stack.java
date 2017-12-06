package classes;


public interface Stack<E> { 
	   /** Accessor Method. 
	  Returns the size of the current instance 
	   **/
	   int size(); 

	   /** Accessor Method. 
		  Returns true if the current instance is empty; false, 
			  if not  
	   **/ 
	   boolean isEmpty(); 

	   /** Accessor Method. 
		  Accesses element in the current instance of the stack. 
		  The affected element is the one that has been in the stack
   	  the least time among all its current elements. 
	       Returns reference to the element being accessed. 
		  Throws the announced exception if the stack is empty. 
	   **/ 
	   E top() throws EmptyStackException; 

	   /** Mutator Method. 
		   Adds a new element to the stack.  
	   **/
	   void push(E element); 

	   /** Mutator Method. 
		   Similar to the top method, but this time, the stack is
       altered since the accessed element is also removed from 
		   the stack.   
		  Throws the announced exception if the stack is empty. 
	   **/
	   E pop() throws EmptyStackException; 
	}

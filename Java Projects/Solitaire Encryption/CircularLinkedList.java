
import java.util.Iterator;

public class CircularLinkedList<E> implements Iterable<E> {

	
	
	// Your variables
	Node<E> head;
	Node<E> tail;
	int size;  // BE SURE TO KEEP TRACK OF THE SIZE

	// DONE
	// implement this constructor
	public CircularLinkedList() {
		
		size = 0;
		// Don't need to set head and tail to null, because they are already null
		head = null;
		tail = null;
		
	}
	
	// Added size just in case I need to check how many items are in the Circular Linked List
	public int size() {
		
		return size;
		
	}
	
	public E set (int index, E item) {
		
		// out of bounds handled 
		if (index < 0 || index > size) {
							
			throw new IndexOutOfBoundsException("Index out of Bounds");
							
		}
		
		Node <E> target = getNode(index);
		
		E oldData = target.item;
		
		target.item = item;
		
		return oldData;
		
	}

	public E get (int index) {
		
		return getNode(index).item;
		
	}
	// DONE
	// I highly recommend using this helper method
	// Return Node<E> found at the specified index
	// be sure to handle out of bounds cases
	private Node<E> getNode(int index ) {
		
		// out of bounds handled 
		if (index < 0 || index > size) {
					
			throw new IndexOutOfBoundsException("Index out of Bounds");
					
		}
		
		Node <E> current = head;
		
		for (int i = 0; i < index; i++) {
			
			current = current.next;
			
		}

		return current;
	}


	// attach a node to the end of the list
	public boolean add(E item) {
		this.add(size,item);
		return false;

	}

	// DONE
	// Cases to handle if keeping track of a tail, only three are needed for regular Linked List:
	// out of bounds
	// adding to an empty list
	// adding to the front, or head
	// adding to the "end", or tail
	// adding anywhere else
	// REMEMBER TO INCREMENT THE SIZE
	public void add(int index, E item){
		
		// out of bounds handled 
		if (index < 0 || index > size) {
			
			throw new IndexOutOfBoundsException("Index out of Bounds");
			
		}
		
		Node <E> adding = new Node<>(item);
		
		// adding to an empty list
		
		if (size == 0) {
			
			this.head = adding;
			this.tail = adding;
			// This connects the head to the tail
			tail.next = head;
		
		// Adding to the head is right below this comment.
			
		} else if (index == 0) {
			
			adding.next = head;
			head = adding;
			// This connects the head to the tail
			tail.next = head;
			
		// Adding to the end or the tail is right below this comment.
			
		} else if (index == size) {
			
//			adding.next = head;
			tail.next = adding;
			tail = adding;
			adding.next = head;
			
		// Adding anywhere else is right below this comment.	
			
		} else {
			
			Node <E> before = getNode(index -1);
			
			adding.next = before.next;
			
			before.next = adding;
			
		}
		
		size ++;

	}

	

	
	// DONE
	// remove must handle the following cases, which are the same as the adding method right
	// above this remove method, but removing the only thing in the list replaces adding to an
	// empty list:
	// out of bounds
	// removing the only thing in the list
	// removing the first thing in the list (need to adjust the last thing in the list to point to the beginning)
	// removing the last thing 
	// removing any other node
	// REMEMBER TO DECREMENT THE SIZE
	public E remove(int index) {
		
		// out of bounds handled 
		if (index < 0 || index >= size) {
			
			throw new IndexOutOfBoundsException("Index out of Bounds");
			
		}
		
		E toReturn = null;
		
		// removing the only thing in the list, so the size has to be equal to 1.
		
		if (size == 1) {
			
			toReturn = head.item;
			
			//is this right?
			head = null;
			tail = null;
		
		// Removing the head is right below this comment.
			
		} else if (index == 0) {
			toReturn = head.item;
			head = head.next;
		// This connects the head to the tail
			tail.next = head;
			
		// Removing the end or the tail is right below this comment.
			
		} else if (index == size -1) {
			
			toReturn = tail.item;
			tail  = getNode(index -1);
			tail.next = head;
		// Removing anywhere else is right below this comment.	
			
		} else {
			
			Node <E> before = getNode(index -1);
			
			toReturn = before.next.item;
			
			before.next = before.next.next;
			
		}
		
		size --;
		
		return toReturn;
	}
	
	
	
	
	// Turns your list into a string
	// Useful for debugging
	public String toString(){
		Node<E> current =  head;
		StringBuilder result = new StringBuilder();
		if(size == 0){
			return "";
		}
		if(size == 1) {
			return head.item.toString();
			
		}
		else{
			do{
				result.append(current.item);
				result.append(" ==> ");
				current = current.next;
			} while(current != head);
		}
		return result.toString();
	}
	
	
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}
	
	// provided code for different assignment
	// you should not have to change this
	// change at your own risk!
	// this class is not static because it needs the class it's inside of to survive!
	private class ListIterator<E> implements Iterator<E>{
		
		Node<E> nextItem;
		Node<E> prev;
		int index;
		
		@SuppressWarnings("unchecked")
		//Creates a new iterator that starts at the head of the list
		public ListIterator(){
			nextItem = (Node<E>) head;
			index = 0;
		}

		// returns true if there is a next node
		// this is always should return true if the list has something in it
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return size != 0;
		}
		
		// advances the iterator to the next item
		// handles wrapping around back to the head automatically for you
		public E next() {
			// TODO Auto-generated method stub
			prev =  nextItem;
			nextItem = nextItem.next;
			index =  (index + 1) % size;
			return prev.item;
	
		}
		
		// removed the last node was visited by the .next() call 
		// for example if we had just created a iterator
		// the following calls would remove the item at index 1 (the second person in the ring)
		// next() next() remove()
		public void remove() {
			int target;
			if(nextItem == head) {
				target = size - 1;
			} else{ 
				target = index - 1;
				index--;
			}
			CircularLinkedList.this.remove(target); //calls the above class
		}
		
	}
	
	// It's easiest if you keep it a singly linked list
	// SO DON'T CHANGE IT UNLESS YOU WANT TO MAKE IT HARDER
	private static class Node<E>{
		E item;
		Node<E> next;
		
		public Node(E item) {
			this.item = item;
		}
		
	}
	
	// This main is used to test the circular linked list
	public static void main(String[] args){
		
		CircularLinkedList <Integer> deckOfCards = new CircularLinkedList <Integer>();
		
		int [] numbers = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 3, 6, 9, 12, 15, 18, 21, 24, 27, 2, 5, 8, 11, 14, 17, 20, 23, 26} ;
		
		for (int i = 0; i < numbers.length; i++) {
			
			deckOfCards.add(numbers[i]);
			
		}
		
		System.out.println(deckOfCards);
		
		
		// THE REASON 1 + 1 DOESNT WORK IS BECASUE IT IS SWITCHING WITH 2, WHICH MAKES IT HIT 27 AGAIN AND IT KEEPS GOING. 
		// I ADDED BREAK TO THE END SO IT WONT CONTINUE, TRY THIS IN THE ACTUAL HW
		
		/* int x = 0;
		
		for (int i = 0; i < deckOfCards.size(); i++) {
			
			if (deckOfCards.get(i) == 27) {
				
				// What's in location i + 1 is still there
				x = deckOfCards.get(i + 1);
				
				// x is 2
				System.out.println(x);
				
				// i is 18
				//System.out.println(i);
				
				// i is 19
				//System.out.println(i + 1);
				
				//System.out.println(deckOfCards);
			
				deckOfCards.set(i, x);
			
				deckOfCards.set(i + 1, 27);
				
				break;
				
			} else if (deckOfCards.get(i + 1) == null) {
				
				x = deckOfCards.get(0);
				
				deckOfCards.set(i, x);
				
				deckOfCards.set(0, 27);
				
				break;
				
			}

			
		}
		
		System.out.println(deckOfCards);
		
		
		
	} */
		
		int x = 0;
		
		for (int i = 0; i < deckOfCards.size(); i++) {
			
			if (deckOfCards.get(i) == 28) {
				
				if (deckOfCards.get(i + 3) == null) {
					
					
					
				}
				
				deckOfCards.add(i + 3, 28);
				
				deckOfCards.remove(i);
				
				break;
				
			} else if (deckOfCards.get(i + 1) == null) {
				
				deckOfCards.add(1, 28);
				
				break;
				
			} else if (deckOfCards.get(i + 2) == null) {
				
				deckOfCards.add(2, 28);
				
				break;
				
			}

			
		}
		
		System.out.println(deckOfCards);
		
		
	}


	
	
}

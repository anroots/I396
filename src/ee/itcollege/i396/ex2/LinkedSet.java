package ee.itcollege.i396.ex2;

import java.util.List;

public class LinkedSet {
	
	private Node head;
	private Node tail;
	
	public LinkedSet() {
		head = tail = new Node(null, null, null);
	}
	
	int size() {
		int size = 0;
		Node run = head.next;
		
		while (run != null) {
			size++;
			run = run.next;
		}
		
		return size;
	}
	
	public boolean contains(Object o) {
		Node run = head.next;
		
		while (run != null) {
			if (run.data.equals(o)) {
				return true;
			}
			run = run.next;
		}
		return false;
	}
	
	public void add(Object o) {
		if (this.contains(o)) {
			return;
		}
		tail.next = new Node(o, this.tail, null);
		tail.next.previous = tail;
		tail = tail.next;
	}
	
	public void remove(Object o) {
		Node run = head.next;
		if (run == null) {
			throw new IllegalStateException();
		}
		
		while (run != null) {
			if (run.data.equals(o)) {
				run.previous.next = run.next;
				if (run.next != null) {
					run.next.previous = run.previous;	
				}
				return;
			}
			
		}
	}
	
	public Boolean containsAll(List<Object> objs) {
		for (Object o : objs) {
			if (!this.contains(o)) {
				return false;
			}
		}
		return true;
	}
	
	private class Node
	{
		private Object data;
		private Node next;
		private Node previous;

		public Node(Object data, Node previous, Node next) {
			this.data = data;
			this.previous = previous;
			this.next = next;
		}
	}
}

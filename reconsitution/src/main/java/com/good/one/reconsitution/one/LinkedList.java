package com.good.one.reconsitution.one;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E>,Deque<E> {
	
	private Node<E> first;
	
	private Node<E> last;
	
	private int size;
	
	private int modCount;
	
	public LinkedList() {
	} 
	
	public LinkedList(Collection< ? extends E> list) {
		this();
		addAll(list);
	}
	
	
	// 内部类
	private static class Node<E> {
		E item;
		Node<E> prev;
		Node<E> next;
		
		public Node(){}
		
		public Node(Node<E> prev, E item, Node<E> next) {
			this.prev = prev;
			this.item = item;
			this.next = next;
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return first == null ? true : false;
	}

	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean add(E e) {
		linkLast(e);
		return true;
	}
	
	void linkLast(E e){
		Node<E> l = last;
		
		Node<E> newNode = new Node(last, e, null);
		
		if (last == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		last = newNode;
		size++;
		modCount++;
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Collection<? extends E> c) {
		return addAll(size,c);
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		checkPositionIndex(index);
		
		Object[] list = c.toArray();
		
		int numNew = list.length;
		if (numNew == 0) return false;
		
		Node<E> pre,next; 
		
		if (index == size) {
			pre = last;
			next = null;
		} else {
			next = node(index);
			pre = next.prev;
		}
		
		
		for (int i = 0; i < numNew; i++) {
			E e = (E)list[i];
			Node<E> newNode = new Node(pre, e, null);
			if (pre == null) {
				first = newNode;
			} else {
				pre.next = newNode;
			}
			pre = newNode;
		}
		
		if (next == null) {
			last = pre;
		} else {
			pre.next = next;
			next.prev = pre;
		}
		size += numNew;
		modCount ++;
		return true;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		Node<E> a = first;
		while (a != null) {
			Node<E> next = a.next;
			a.prev = null;
			a.next = null;
			a.item = null;
			a = next;
		}
		first = last = null;
		size = 0;
		modCount ++;
	}

	public E get(int index) {
		checkPositionIndex(index);
		return node(index).item;
	}

	public E set(int index, E element) {
		checkPositionIndex(index);
		E oldValue = node(index).item; 
		node(index).item = element;
		return oldValue;
	}

	public void add(int index, E element) {
		checkPositionIndex(index);
		
		if (index == size) {
			linkLast(element);
		}else {
			linkBefore(node(index),element);
		}
		
	}
	
	private void linkBefore(Node<E> node, E element) {
		Node<E> newNode = new Node(node.prev, element, node);
		
		if (null == node.prev) {
			// 为null 就是第一个
			first = newNode;
		} else {
			node.prev.next = newNode;
			node.prev = newNode;
		}
		size++;
		modCount++;
		
	}

	private void checkPositionIndex(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("index: "+ index + " size: " + size);
	}

	private Node<E> node(int index) {
		
		checkPositionIndex(index);
		Node l = first;
		
		for (int i = 0; i < size ;i++) {
			if (i == index) {
				return l;
			} else {
				l = l.next;
			}
		}
		return null;
	}

	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int indexOf(Object o) {
		Node<E> temp = first;
		for (int i = 0; i < size; i++) {
			if (o == null && temp.item == o) {
				return i;
			} else if (o.equals(temp.item)) {
				return i;
			}
			temp = first.next;
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		int index = size;
		if (null == o) {
			for (Node<E> e = last; e != null; e = e.prev) {
				if (e.item == o) {
					return index;
				}
				index-- ;
			}
		} else {
			for (Node<E> e = last; e != null; e = e.prev) {
				if (o.equals(e.item)) {
					return index;
				}
				index-- ;
			}
		}
		return -1;
	}

	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addFirst(E e) {
		linkFirst(e);
	}

	private void linkFirst(E e) {
		Node<E> newNode = new Node(null,e,first); 
		
		if (first == null) {
			last = newNode;
		}
		first.prev = newNode;
		
		first = newNode;
		modCount++;
		size++;
	}

	public void addLast(E e) {
		linkLast(e);
	}

	public boolean offerFirst(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean offerLast(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	public E removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	public E pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	public E pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	public E getFirst() {
		final Node<E> f = first;
		if (f == null) throw new NoSuchElementException();
		return f.item;
	}

	public E getLast() {
		final Node<E> l = first;
		if (l == null) throw new NoSuchElementException();
		return l.item;
	}

	public E peekFirst() {
		return first == null ? null : first.item; 
	}

	public E peekLast() {
		return last == null ? null : last.item;
	}

	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	public E element() {
		return getFirst();
	}

	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	public void push(E e) {
		// TODO Auto-generated method stub
		
	}

	public E pop() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}

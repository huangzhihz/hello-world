package com.good.one.java.guide.first;

import java.util.Arrays;

/*自己实现一个栈，要求这个栈具有push()、pop()（返回栈顶元素并出栈）、peek() （返回栈顶元素不出栈）、isEmpty()、size()这些基本的方法。

提示：每次入栈之前先判断栈的容量是否够用，如果不够用就用Arrays.copyOf()进行扩容*/

public class Mystack {
	
	private int[] data;
	
	// 容量
	private int capacity;
	
	// 存入的数量
	private int count;

	private static final int INCREASE_SIZE = 2;
	
	private static final int MAX_SIZE = Integer.MAX_VALUE - 8;
	

	public Mystack () {
		capacity = 8;
		data = new int[8];
		count = 0;
	}
	
	public Mystack (int initSize) {
		if (initSize <= 0) throw new IllegalAccessError("initSize must greather than zero"); 
		capacity = initSize;
		data = new int[initSize];
		count = 0;
	}
	
	// ensureCapacity
	public void push (int value) {
		if (count == capacity) {
			ensureCapacity(count);
		}
		if (count > Integer.MAX_VALUE) throw new IndexOutOfBoundsException("stack is Max Values");
		
		data[count++] = value; 
	}
	
	
	// 返回栈顶元素并出栈
	public int pop () {
		if (--count < 0) {
			count =0; 
			throw new IllegalAccessError("stack is empty");
		}
		return data[count];
	}
	
	// 返回栈顶元素不出栈
	public int peek () {
		if (count == 0) throw new IllegalAccessError("stack is empty");
		return data[count-1];
	}
	
	public void ensureCapacity (int minSize) {
		if (minSize < 8)  minSize = 8; 
		int newSize = capacity * INCREASE_SIZE;
		if (newSize < minSize) newSize = minSize;
		
		if (newSize > MAX_SIZE) {
			if (minSize > MAX_SIZE) {
				newSize = Integer.MAX_VALUE;
			} else {
				newSize = MAX_SIZE;
			}
		}
		
		data = Arrays.copyOf(data, newSize);
		capacity = newSize;
	}
	
	
	public boolean isEmpty () {
		return count == 0;
	}
	
	public int size() {
		return count;
	}
	
	public static void main (String[] args) {
		Mystack myStack = new Mystack(3);
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		myStack.push(4);
		myStack.push(5);
		myStack.push(6);
		myStack.push(7);
		myStack.push(8);
		System.out.println(myStack.peek());//8
		System.out.println(myStack.size());//8
		for (int i = 0; i < 8; i++) {
		    System.out.println(myStack.pop());
		}
		System.out.println(myStack.isEmpty());//true
		myStack.pop();//报错：java.lang.IllegalArgumentException: Stack is empty.
	}
	
}

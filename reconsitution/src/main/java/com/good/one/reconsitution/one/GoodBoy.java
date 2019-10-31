package com.good.one.reconsitution.one;

import java.util.concurrent.SynchronousQueue;

public class GoodBoy<E> {

	Node<E> first;

	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	
	public E getFirst() {
		// return first != null ? first.item : null;
		final Node<E> f = first;
		return f != null ? f.item : null;
	}
	// 值传递
	public static void main (String [] args) {
		/*GoodBoy<Integer> a = new GoodBoy<Integer>();
		a.first = new Node(null,1,null);
		System.out.println(a.first.toString());
		Node<Integer> b = new Node<Integer>(null, 2, a.first);
		
		a.first = b;
		System.out.println(b.next);
		System.out.println(a.first);*/
		
		/*GoodBoy<StringBuffer> a = new GoodBoy<StringBuffer>();
		StringBuffer c = new StringBuffer("123245D");
		a.first = new Node(null,c,null);
		
		StringBuffer b =  a.getFirst();
		
		System.out.println(a.getFirst());
	
		b.append("hz");
		
		System.out.println(a.getFirst());*/
		
		Integer a = -5;
		// 在计算机里面负数是操作在补码上 而且>>> 无符号右移 左边是补0的  (符号位不在转码范围,但是在移位的范围)
		// 操作在补码 （所以负数右移其实在二进制补0，正数补0是同样的效果）  有符号位右移>>是补1 所以结果是除2(符号位不在转码范围)
		// 左移也是操作在补码 正负右边都补0 相当于*2
		System.out.println(Integer.toBinaryString(a));
		a = a >>> 1;
		System.out.println(a);
		System.out.println(Integer.toBinaryString(a)); 
	}
}

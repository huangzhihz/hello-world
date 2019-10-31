package com.good.one.reconsitution.one;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class HashMap<K,V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable{

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}/*

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}


	*//**
	 * 
	 *//*
	private static final long serialVersionUID = -6196744197229338595L;

	// 初始化16
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

	static final int MAXIMUM_CAPACITY = 1 << 30;

	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	static final int TREEIFY_THRESHOLD = 8;

	static final int UNTREEIFY_THRESHOLD = 6;

	// 桶中结构转化为红黑树对应的table的最小大小  /？/
	static final int MIN_TREEIFY_CAPACITY = 64;

	static class Node<K,V> implements Map.Entry<K, V> {
		final int hash;
		final K key;
		V value;
		Node<K,V> next;

		// 构造器也是给final参数初始化的过程
		public Node(int hash, K key, V value, Node<K, V> next) {
			super();
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public final K getKey() {
			return key;
		}

		public final V getValue() {
			return value;
		}

		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}

		public final int hashCode () {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}

		public final boolean equals(Object o) {
			if (o == this) return true;
			if (o instanceof Map.Entry) {
				// 直接用<K, V> 会有警告
				Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
				if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) return true;
			}
			return false; 
		}


	}

	// hash挠动函数
	static final int hash(Object key) {
		int h;
		return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	// 实现了比较器就返回本身的class对象 否则返回null
	static Class<?> comparableClassFor(Object x) {
		if (x instanceof Comparable) {
			Class<?> c; 
			Type[] ts,as; 
			Type t; 
			ParameterizedType p;
			// 如果是String的class 就返回class
			if ((c = x.getClass()) == String.class)
				return c;
			// getInterfaces 该方法可以将方法实现的接口class对象用数组返回
			// getGenericInterfaces 回表示由此对象表示的类或接口直接实现的接口的{@code Type
			//区别在于getGenericInterfaces可以返回其参数化类型，例如： Collection<String>、 List<Coupon>中的String和Coupon
			if((ts = c.getGenericInterfaces()) != null) {
				// ParameterizedType.getOwnerType() 返回参数内部类的上级类
				// ParameterizedType.getActualTypeArguments 获取参数类型
				// ParameterizedType.getRawType 获取接口的类型
				// 如果接口实现不为null
				for (int i = 0; i < ts.length; ++i) {
					// 如果实现的接口有参数 并且接口的类型为比较器 并且比较器的参数是object.class 就返回c.class对象
					if (((t = ts[i]) instanceof ParameterizedType) &&
							((p = (ParameterizedType)t).getRawType() == 
							Comparable.class) &&
							(as = p.getActualTypeArguments()) != null 
							&& as.length == 1 && as[0] == c)
						return c;
				}
			}
		}
		return null;
	}

	// k.compareTo(x) 如果x的class对象不为kc return 0
	@SuppressWarnings({"rawtypes","unchecked"})
	static int compareComparables(Class<?> kc, Object k, Object x) {
		return (x == null || x.getClass() != kc) ? 0 :
			((Comparable)k).compareTo(x);
	}

	// 将size 转为2的幂次方
	static final int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}
	
	// transient 是因为避免在不同机器上因为hashcode的不同导致元素存放的位置一样而导致查询错误;同时要排除掉将空的内存序列化
	transient Node<K,V>[] table;
	
	// /？/
	transient Set<Map.Entry<K, V>> entrySet;
	
	// 放了多少个元素 和数组大小不一样
	transient int size;
	
	// 操作map的次数
	transient int modCount;
	
	// 要调整大小的下一个大小值(容量/负载因子||capacity/load factor/)。 如果没有初始化则为初始默认或zero
	int threshold;

	// 负载因子 如果没有初始化会提示异常
    final float loadFactor;
    
    public HashMap(int initialCapacity, float loadFactor) {
    	if (initialCapacity < 0)
    		throw new IllegalArgumentException("Illegal initial capacity:" + 
    											initialCapacity);
    	if (initialCapacity > MAXIMUM_CAPACITY)
    		initialCapacity = MAXIMUM_CAPACITY;
    	if (loadFactor < 0 || Float.isNaN(loadFactor))
    		throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
    	this.loadFactor = loadFactor;
    	// /?/
    	this.threshold = tableSizeFor(initialCapacity);
    }
    
    public HashMap(int initialCapacity) {
    	this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    
    public HashMap() {
    	this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
     
    final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
    	int s = m.size();
    	if (s >0) {
    		if (table == null) {
    			// 初始的时候获取数组长度
    			// 获取数组大小
    			float ft = ((float)s / loadFactor) + 1.0F;
    			// 拿出最大的值
    			int t = ((ft < (float)MAXIMUM_CAPACITY) ? 
    					(int)ft : MAXIMUM_CAPACITY);
    			// 如果大于表大小 否则就拿原有threshold
    			if (t > threshold) threshold = tableSizeFor(t);
    		} 
    		else if (s > threshold)
    			// 不为null 且大于threshold /?/
    			resize();
    		for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
    			K key = e.getKey();
    			V value = e.getValue();
    			putVal(hash(key), key, value, false, evict);
    		}
    	}
    }
    
    final Node<K, V>[] resize() {
    	Node<K,V>[] oldTab =table;
    	
    	int oldCap = (oldTab == null) ? 0 : oldTab.length;
    	int oldThr = threshold;
    	int newCap, newThr = 0;
    	
    	if (oldCap > 0) {
    		if (oldCap >= MAXIMUM_CAPACITY) {
    			threshold = Integer.MAX_VALUE;
    			return oldTab;
    		}
    		else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
    				oldCap >= DEFAULT_INITIAL_CAPACITY) {
    			newThr = oldThr << 1;
    		}
    	}
    	else  if (oldThr > 0)
    		newCap = oldThr;
    	else {
    		newCap = DEFAULT_INITIAL_CAPACITY;
    		// /?/
    		newThr = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    	}
    	if (newThr == 0) {
    		float ft = (float) newCap * loadFactor;
    		newThr =(newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
    				(int)ft : Integer.MAX_VALUE);
    	}
    	threshold = newThr;
    	@SuppressWarnings({"rawtypes", "unchecked"})
    		Node<K, V>[] newTab = (Node<K, V>[])new Node[newCap];
    	table = newTab;
    	if (oldTab != null) {
    		for (int j = 0; j < oldCap; ++j) {
    			Node<K, V> e;
    			if ((e = oldTab[j]) != null) {
    				oldTab[j] = null;
    				if (e.next == null)
    					newTab[e.hash & (newCap - 1)] = e;
    				else if (e instanceof TreeNode)
    					((TreeNode<K, V>)e).
    			}
    		}
    	}
    	
    }
    
    static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }
    
    static final class TreeNode<K, V> extends HashMap.Entry<K,V> {
    	TreeNode<K, V> parent;
    	TreeNode<K, V> left;
    	TreeNode<K, V> right;
    	TreeNode<K, V> prev;
    	boolean red;
    	
		TreeNode(int hash, K key, V value, Node<K, V> next) {
			super(hash, key, value, next);
		}
    	
		final TreeNode<K, V> root () {
			for (TreeNode<K, V> r = this;;) {
				TreeNode<K, V> p = r.parent;
				if ( p == null) return r;
				else r = p;
			}
		}
		
		
    }
    
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}



*/}

package com.good.one.reconsitution.one;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.function.Consumer;

/**
 *	abstractList<E> 数组队列接口，定义了大部分list方法
 *  RandomAccess 标志可以快速随机查找
 *  Serializable 序列化
 * **/
public class ArrayList<E> extends AbstractList<E> 
implements List<E>, RandomAccess, Cloneable, java.io.Serializable 
{

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}/*

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;


	private static final int DEFAULT_CAPACITY = 10;

	// 为空的情况
	private static final Object[] EMPTY_ELMENTDATA = {};

	//用于默认大小空实例的共享空数组实例。
	//我们把它从EMPTY_ELEMENTDATA数组中区分出来，以知道在添加第一个元素时容量需要增加多少。
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATS = {};

	// 为什么用transient:避免将空的位置序列化， 通过writeObject，readObject 进行自定义序列化
	transient Object[] elementData;

	private int size;

	// arrayList 没有最大值除非内存溢出
	public ArrayList(int intialCapacity) {
		if (intialCapacity > 0) {
			this.elementData = new Object[intialCapacity];

		} else if (intialCapacity == 0) {
			this.elementData = EMPTY_ELMENTDATA;
		} else {
			throw new IllegalArgumentException("Illegal Capacity:" + intialCapacity);
		}
	}

	public ArrayList() {
		this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATS;
	}


	*//** 
	 * 列表创建就用他的copyof值或者为空就给EMPTY_ELMENTDATA
	 * **//*
	public ArrayList(Collection<? extends E> c) {
		elementData = c.toArray();
		if ((size = elementData.length) != 0) {
			if (elementData.getClass() != Object[].class)
				elementData = Arrays.copyOf(elementData, size, Object[].class);
		} else {
			this.elementData = EMPTY_ELMENTDATA;
		}
	}

	*//**
	 * 将数组限定到size大小，去除无效空闲空间 
	 * **//*
	public void trimToSize() {
		modCount++;
		if (size < elementData.length) {
			elementData = (size == 0) ? EMPTY_ELMENTDATA : Arrays.copyOf(elementData, size);
		}
	}

	//  但传入值大于0或初始值才操作(大于存储长度时候扩容)/ 扩容
	public void ensureCapacity (int minCapacity) {
		int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATS) ? 0 : DEFAULT_CAPACITY;

		if (minCapacity > minExpand) {
			ensureExplicitCapacity(minCapacity);
		}
	}

	// 取传入值和初始值最大的进行操作(大于存储长度时候扩容)/ 扩容
	private void ensureCapacityInternal(int minCapacity) { 
		if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATS) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}

		ensureExplicitCapacity(minCapacity);
	}

	// 当传入值大于存储数组长度才 扩容
	private void ensureExplicitCapacity (int minCapacity) {
		modCount ++;

		if (minCapacity - elementData.length > 0)
			grow(minCapacity);
	}

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	//
	private void grow (int minCapacity) {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);

		// 小于传入值取传入值
		if (newCapacity - minCapacity < 0)
			newCapacity = minCapacity;
		// 新扩容大于最大值 给Integer.Max_value 否则等于则给 Max_array_size
		if (newCapacity - MAX_ARRAY_SIZE > 0)
			newCapacity = hugeCapacity(minCapacity);
		// 进行copyof
		elementData = Arrays.copyOf(elementData, newCapacity);
	}


	private static int hugeCapacity (int minCapacity) {
		if (minCapacity < 0)
			throw new OutOfMemoryError();
		return (minCapacity > MAX_ARRAY_SIZE) ?
				Integer.MAX_VALUE :
					MAX_ARRAY_SIZE;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	public int indexOf(Object o) {
		if (o == null) {
			for (int i= 0; i< size; i++) {
				if (elementData[i] == null)
					return i ;
			}
		} else {
			for (int i = 0; i < size; i++)
				if (o.equals(elementData[i]))
					return i;
		}
		return -1;
	};

	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size - 1; i >= 0; i--)
				if (elementData[i] == null) {
					return i;
				}
		} else {
			for (int i = size -1; i >= 0; i--)
				if (o.equals(elementData[i]))
					return i;
		}
		return -1;
	}

	// 进行浅克隆
	public Object clone() {
		try {
			ArrayList<?> v = (ArrayList<?>) super.clone();
			v.elementData = Arrays.copyOf(elementData, size);
			v.modCount = 0;
			return v;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}

	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}

	// 如果a小于size 返回一个新的size数组，大于则返回原数组并且size = null
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (a.length < size)
			return (T[]) Arrays.copyOf(elementData, size, a.getClass());
		System.arraycopy(elementData, 0, a, 0, size);

		if (a.length > size) 
			a[size] = null;
		return a;
	}

	@SuppressWarnings("unchecked")
	E elementData(int index) {
		return (E) elementData[index];
	}

	public E get(int index) {
		rangeCheck(index);

		return elementData(index);
	}

	public E set (int index, E element ) {
		// 为什么set只检验大于size : 如果为负数就会报下标溢出异常
		rangeCheck(index);

		E oldValue = elementData(index);
		elementData[index] = element;
		return oldValue;
	}

	// 先扩容然后进行赋值
	public boolean add(E e) {
		ensureCapacityInternal(size + 1);
		elementData[size++] = e;
		return true;
	}

	public void add (int index, E element) {
		// 检验下线是因为避免System异常，本地方法异常
		rangeCheckForAdd(index);

		// 在扩容的时候 modcount加过一次
		ensureCapacityInternal(size +1);
		System.arraycopy(elementData, index, elementData, index + 1,
				size - index);
		elementData[index]  = element;
		size++;
	}

	public E remove(int index) {
		rangeCheck(index);

		modCount++;
		E oldValue = elementData(index);

		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementData, index+1, elementData, index, 
					numMoved);
		elementData[--size] = null;

		return oldValue;
	}

	// 可以调用index方法优化
	public boolean remove (Object o) {
		if (o == null) {
			for (int index = 0; index < size; index++ )
				if(elementData[index] == null) {
					fastRemove(index);
					return true;
				}
		} else {
			for (int index = 0; index < size; index++ )
				if(o.equals(elementData[index])) {
					fastRemove(index);
					return true;
				}
		}
		return false;
	}

	private void fastRemove(int index) {
		modCount++;
		int numMoved = size - index - 1;

		if (numMoved > 0)
			System.arraycopy(elementData, index + 1, elementData, index,
					numMoved);

		elementData[--size] = null;

	}

	public void clear() {
		modCount++;

		for (int i = 0; i < size; i++)
			elementData[i] = null;

		size = 0;
	}

	// 增加所有
	public boolean addAll(Collection<? extends E> c) {
		Object[] a= c.toArray();
		int numNew = a.length;
		ensureCapacityInternal(size + numNew);
		// 源数组 起始位 目标数组 起始位 复制长度
		System.arraycopy(a, 0, elementData, size, numNew);
		size += numNew;
		return numNew != 0;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		// 用下线验证避免本地方法报错
		rangeCheckForAdd(index);

		Object[] a = c.toArray();
		int numNew = a.length;
		ensureCapacityInternal(numNew);

		int numMoved = size - index;
		if (numMoved > 0)
			System.arraycopy(elementData, index, elementData, index + numNew,
					numMoved);
		System.arraycopy(a, 0, elementData, index, numNew);
		size += numNew;
		return numNew != 0;
	}

	// 复制toIndex到size 覆盖fromIndex后面
	protected void removeRange(int fromIndex, int toIndex) {
		modCount++;
		int numMoved = size - toIndex;

		// 复制toIndex到size 覆盖fromIndex后面
		System.arraycopy(elementData, toIndex, elementData, fromIndex,
				numMoved);

			复制大小 size - toIndex
			失去大小 size - fromIndex
			最后大小 size + (size - toIndex) - (size - fromIndex)
			=size - toIndex + fromIndex = size - (toIndex - fromIndex)
		// 将toIndex-size 覆盖掉 toIndex-size之后的
		int newSize = size - (toIndex - fromIndex);


		for (int i = newSize; i < size; i++)
			elementData[i] = null;
		size = newSize;
	}

	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c);
		return batchRemove(c, false);
	}

	 
	 * complement true
	 * 留下在c的 去除不在
	 * complement false
	 * 留下不在的 去除在的
	 * 
	private boolean batchRemove(Collection<?> c, boolean complement) {
		final Object[] elementData = this.elementData;
		int r = 0, w = 0;
		boolean modified = false;
		try {
			
			 * complement true
			 * 将包括在c中的前移 不在的去除
			 * complement false
			 * 将不包括c中的迁移 在的去除
			 			
			for (; r < size; r++)
				if (c.contains(elementData[r]) == complement )
					elementData[w++] = elementData[r];
		} finally {
			// 只循环到r 将r后面的数据复制w后面
			if (r != size) {
				System.arraycopy(elementData, r, 
						elementData, w, size - r);
				w += size -r;
			}
			
			 * 如果是没有循环完: 有修改也进行统计次数和置空 那r后面的数据就会被抛弃			
			 * 如果是循环完: w是留下的数据 将w后面数据置空
			 * 修改了size - w 次 就是去除了这么多条数据
			 			
			if (w != size) {
				for (int i = w; i < size; i++)
					elementData[i] = null;
				modCount += size - w;
				size = w;
				modified = true;
			}
		}

		return modified;
	}


	// 利用反射调用  写入流中(本地)
	private void writeObject(java.io.ObjectOutputStream s)
			throws java.io.IOException {
		// 获取修改过的次数
		int expectedModeCount = modCount;
		//	上行调用类定义的writeObject方法时的上下文，持有当前被序列化的对象和当前对象描述符。在非writeObject上行调用时为null
		// 将一般的数据序列化 用上下文放入對象
		s.defaultWriteObject();

		// 設置寫入的長度
		s.writeInt(size);

		for (int i = 0; i < size; i++)
			s.writeObject(elementData[i]);
		if (modCount != expectedModeCount) {
			// 如果在写入的时候对象还被更改 就抛出异常
			throw new ConcurrentModificationException();
		}
	}

	private void readObject(java.io.ObjectInputStream s) 
			throws java.io.IOException, ClassNotFoundException {
		elementData = EMPTY_ELMENTDATA;

		s.defaultReadObject();

		// 設置讀出的長度
		s.readInt();

		if (size > 0) {
			ensureCapacityInternal(size);

			Object[] a = elementData;

			for (int i = 0; i < size; i++) {
				a[i] = s.readObject();
			}
		}
	}


	public ListIterator<E> listIterator (int index) {
		if (index < 0 || index > size)
			throw new  IndexOutOfBoundsException("Index: " + index);
		return new ListItr(index);
	}

	public ListIterator<E> listIterator() {
		return new ListItr(0);
	}

	public Iterator<E> iterator() {
		return new Itr();
	}

	private class Itr implements Iterator<E> {
		int cursor; // 下一個結點
		int lastRet = -1; // 返回的已經被取出的最后一个元素（當前結點）的索引;如果没有返回-1
		int expectedModCount = modCount; // 用來保障list數據的一致性

		public boolean hasNext() {
			return  cursor != size;
		}


		@SuppressWarnings("unchecked")
		public E next() {
			checkForComodification();
			int i = cursor;
			if (i >= size) {
				// 超出範圍
				throw new NoSuchElementException();
			}
			Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length)
				// 下一個比長度大
				throw new ConcurrentModificationException();
			cursor = i + 1;
			return (E) elementData[lastRet = i];
		}

		// 去除当前的結點
		public void remove() {
			if (lastRet < 0)
				// 沒有當前結點
				throw new IllegalStateException();
			checkForComodification();

			try {
				ArrayList.this.remove(lastRet);
				cursor = lastRet;
				// 当前结点消失
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}

		}

		// 从下个结点赋值给consumer
		@SuppressWarnings("unchecked")
		public void forEachRemaining (Consumer<? super E> consumer) {
			Objects.requireNonNull(consumer);
			// 獲取list的大小
			final int size = ArrayList.this.size;

			int i = cursor;
			if (i >= size) {
				return;
			}
			// 獲取數據
			final Object[] elementData = ArrayList.this.elementData;

			if (i >= elementData.length) {
				// 下一個超出了數據
				throw new ConcurrentModificationException();
			}

			// 将下个结点的数据重新给如？
			while (i != size && modCount == expectedModCount) {
				consumer.accept((E) elementData[i++]);
			}

			cursor = i;
			lastRet = i - 1;
			checkForComodification();
		}
		final void checkForComodification() {
			if (modCount != expectedModCount) 
				// 里外不一致 多線程影響
				throw new ConcurrentModificationException();
		}

	} 

	//
	private class ListItr extends Itr implements ListIterator<E> {
		ListItr(int index) {
			super();
			cursor = index;
		}

		public boolean hasPrevious() {
			return cursor != 0;
		}

		public int nextIndex() {
			return cursor;
		}

		public int previousIndex() {
			return cursor - 1;
		}

		// 返回当前且指标往前移动一位
		@SuppressWarnings("unchecked")
		public E previous() {
			checkForComodification();
			int i = cursor - 1;
			if (i < 0)
				throw new NoSuchElementException();
			Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i;
			// 返回当前且指标往前移动一位
			return (E)elementData[lastRet = i];
		}

		// 在当前位置存储值
		public void set(E e) {
			if (lastRet < 0)
				throw new IllegalStateException();
			checkForComodification();

			try {
				ArrayList.this.set(lastRet, e);
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}

		}

		// 将下个结点添加e,同时跳过新增的结点，下结点访问原下结点
		public void add(E e) {
			checkForComodification();

			try {
				int i = cursor;
				ArrayList.this.add(i,e);
				cursor = i + 1;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

	}

	public List<E> subList(int fromIndex, int toIndex) {
		subListRangeCheck(fromIndex, toIndex, size);
		return new SubList(this, 0, fromIndex, toIndex);
	}

	static void subListRangeCheck(int fromIndex, int toIndex, int size) {
		if (fromIndex < 0)
			throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
		if (toIndex > size)
			throw new IndexOutOfBoundsException("toIndex = " + toIndex);
		if (fromIndex > toIndex)
			throw new IllegalArgumentException("fromIndex(" + fromIndex +
					") > toIndex(" + toIndex + ")");
	}


	private void rangeCheckForAdd(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private void rangeCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private String outOfBoundsMsg(int index) {
		return "Index: "+index+", Size: "+size;
	}

	private class SubList extends AbstractList<E> implements RandomAccess {
		private final AbstractList<E> parent;
		private final int parentOffset;
		private final int offset;
		int size;

		SubList(AbstractList<E> parent,
				int offset, int fromIndex, int toIndex) {
			this.parent = parent;
			this.parentOffset = fromIndex;// 父偏移量
			this.offset = offset + fromIndex;// 偏移量是offset + 父偏移量
			this.size = toIndex - fromIndex;// 大小是
			this.modCount = ArrayList.this.modCount;
		}

		// 将带偏移量的值更换
		public E set(int index, E e) {
			rangeCheck(index);
			checkForComodification();
			E oldValue = ArrayList.this.elementData(offset + index);
			ArrayList.this.elementData[offset + index] = e;
			return oldValue;
		}

		public E get(int index) {
			rangeCheck(index);
			checkForComodification();
			return  ArrayList.this.elementData(offset + index);
		}

		public int size() {
			checkForComodification();
			return this.size;
		}

		public void add(int index, E e) {
			rangeCheckForAdd(index);
			checkForComodification();
			parent.add(parentOffset + index, e);
			this.modCount = parent.modCount;
			this.size++;
		}

		public E remove(int index) {
			rangeCheck(index);
			checkForComodification();
			E result = parent.remove(parentOffset + index);
			this.modCount = parent.modCount;
			this.size--;
			return result;
		}

		// 清空parentOffset + fromIndex 的 toIndex - fromIndex 大小数据
		protected void removeRange(int fromIndex, int toIndex) {
			checkForComodification();
			parent.removeRange(parentOffset + fromIndex,
					parentOffset + toIndex);
			this.modCount = parent.modCount;
			this.size -= toIndex - fromIndex;
		}

		public boolean addAll(Collection<? extends E> c) {
			return addAll(this.size, c);
		}

		public boolean addAll(int index, Collection<? extends E> c) {
			rangeCheckForAdd(index);
			int cSize = c.size();
			if (cSize==0)
				return false;

			checkForComodification();
			parent.addAll(parentOffset + index, c);
			this.modCount = parent.modCount;
			this.size += cSize;
			return true;
		} 

		public Iterator<E> iterator() {
			return listIterator();
		}

		public ListIterator<E> listIterator(final int index) {
			checkForComodification();
			rangeCheckForAdd(index);
			final int offset = this.offset;

			return new ListIterator<E>(){
				int cursor = index;
				int lastRet = -1;
				int expectedModCount = ArrayList.this.modCount;

				public boolean hasNext() {
					return cursor != SubList.this.size;
				}

				// 带偏移量 返回当前值且王后移动
				public E next() {
					checkForComodification();
					int i = cursor;
					if (i >= SubList.this.size)
						throw new NoSuchElementException();
					Object[] elementData = ArrayList.this.elementData;
					if (offset + i >= elementData.length)
						throw new ConcurrentModificationException();
					cursor = i + 1;
					return (E) elementData[offset + (lastRet = i)];
				}

				public boolean hasPrevious() {
					return cursor != 0;
				}

				@SuppressWarnings("unchecked")
				public E previous() {
					checkForComodification();
					int i = cursor - 1;
					if (i < 0)
						throw new NoSuchElementException();
					Object[] elementData = ArrayList.this.elementData;
					if (offset + i >= elementData.length)
						throw new ConcurrentModificationException();
					cursor = i;
					return (E) elementData[offset + (lastRet = i)];
				}

				@SuppressWarnings("unchecked")
				public void forEachRemaining(Consumer<? super E> consumer) {
					Objects.requireNonNull(consumer);
					final int size = SubList.this.size;
					int i = cursor;
					if (i >= size) {
						return;
					}
					final Object[] elementData = ArrayList.this.elementData;
					if (offset + i >= elementData.length) {
						throw new ConcurrentModificationException();
					}
					while (i != size && modCount == expectedModCount) {
						consumer.accept((E) elementData[offset + (i++)]);
					}
					// update once at end of iteration to reduce heap write traffic
					lastRet = cursor = i;
					checkForComodification();
				}

				public int nextIndex() {
					return cursor;
				}


				// lastRet 是当前值也是上一个值（即是返回了的值）
				public int previousIndex() {
					return cursor - 1;
				}

				public void set(E e) {
					if (lastRet < 0)
						throw new IllegalStateException();
					checkForComodification();

					try {
						ArrayList.this.set(offset + lastRet, e);
					} catch (IndexOutOfBoundsException ex) {
						throw new ConcurrentModificationException();
					}
				}

				// subList天机一个值
				public void add(E e) {
					checkForComodification();

					try {
						int i = cursor;
						SubList.this.add(i, e);
						cursor = i + 1;
						lastRet = -1;
						expectedModCount = ArrayList.this.modCount;
					} catch (IndexOutOfBoundsException ex) {
						throw new ConcurrentModificationException();
					}
				}

				final void checkForComodification() {
					if (expectedModCount != ArrayList.this.modCount)
						throw new ConcurrentModificationException();
				}

			};
		}

		public List<E> subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new SubList(this, offset, fromIndex, toIndex);
        }

		
		private void rangeCheck(int index) {
			if (index < 0 || index >= this.size)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}

		private void rangeCheckForAdd(int index) {
			if (index < 0 || index > this.size)
				throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}

		private String outOfBoundsMsg(int index) {
			return "Index: "+index+", Size: "+this.size;
		}

		private void checkForComodification() {
			if (ArrayList.this.modCount != this.modCount)
				throw new ConcurrentModificationException();
		}
	}



*/}

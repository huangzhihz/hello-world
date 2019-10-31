package com.good.one.java.guide.first;

public class JavaBasicsOne {
	//自己设计一个泛型的获取数组最小值的函数.并且这个方法只能接受Number的子类并且实现了Comparable接口。
	public static  <T extends Number & Comparable<? super T>> T  min (T[] values) {
		if (values.length <0 || values.length == 0)  return null;
		T min = values[0];
		for (T t : values) {
			if (min.compareTo(t) > 0) min = t; 
		}
		return min;
	}
	
	
	
	public static void main (String [] args) {
		System.out.println(TestEnum.FJ.toString());
		/*int minInteger = min(new Integer[]{1, 2, 3});//result:1
		double minDouble = min(new Double[]{1.2, 2.2, -1d});//result:-1d
		System.out.println("最小的Integer: " + minInteger + "最小的minDouble: " + minDouble);*/
	}
}

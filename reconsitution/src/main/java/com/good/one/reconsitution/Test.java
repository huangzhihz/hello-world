package com.good.one.reconsitution;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Test {

	private List<String> beanNames = new ArrayList<>();


	void Test() {

	}
	// 扫描包 已经是.class文件了
	void doScanner(String dirPath) {
		// ?
		URL url = this.getClass().getClassLoader().getResource("/" + dirPath.replaceAll(".", "/"));
		File dir = new File(url.getFile());
		// 所有目录的绝对路径
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				doScanner(dirPath + "." + file.getName());
				continue;
			}

			String beanName = dirPath + "." + file.getName().replaceAll(".class", "");
			beanNames.add(beanName);
		}
	}

	public static void main (String [] args) {
		Test test= new Test();
		
		test.doScanner("one");
	}

}

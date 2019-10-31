package com.good.one.java.guide.first;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class NIO {
	
	public static void main (String [] args) {
		writeNIO();
	}
	
	static void writeNIO() {
		String filename = "out.txt";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(filename));
			
			// 与BIO 区别 写出时候以块数据写出
			FileChannel channel =fos.getChannel();
			// 字节缓冲
			ByteBuffer src = Charset.forName("UTF-8").encode("钟安娜is 好女孩, 采菊东篱下， 悠然见南山");
			
			// 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
			System.out.println("初始化容量和limit：" + src.capacity() + ","
					+ src.limit());
			
			int length = 0;
			while ((length = channel.write(src)) != 0 ) {
				/*
				 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
				 */
				System.out.println("写入长度：" + length);
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

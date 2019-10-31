package com.good.one.java.guide.first;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.alibaba.fastjson.util.IOUtils;

public class BIO {

	public static void main (String [] args) throws IOException {
		User user = new User();
		user.setName("huangzhi");
		user.setAge(23);
		System.out.println(user);
		
		ObjectOutputStream oos = null;
		try {
			// 将对象用文件流转成object字节流  写出的是在最外层E:\Good_Code_Hz\hello-world\reconsitution
			oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
			oos.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(oos);
		}
		
		File file = new File("tempFile");
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			User newUser = (User)ois.readObject();
			System.out.println(newUser);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(ois);
			file.delete(); 
		}
	}

}

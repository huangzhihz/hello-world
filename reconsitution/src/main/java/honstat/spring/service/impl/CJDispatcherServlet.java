package honstat.spring.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CJDispatcherServlet extends HttpServlet{
	
	//设置
	private Properties properties;

	private List<String> beanNames;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2117896792008155013L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	public void init(ServletConfig config) {
		// 加载配置 将web.xml的initparam获取
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		loadConfig(contextConfigLocation);
		
        // 获取要扫描包地址
		String dirPath = properties.getProperty("scanner.package");
		
        // 扫描被加载类
    
        // 实例化类

        // 加载依赖注入，给属性赋值
 
        // 加载映射地址？
		
	}
	
	// 将配置文件内容放入properties
	void loadConfig(String contextConfigLocation){
		// 将配置文件转成流
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
		try {
			// 将配置文件内容放入properties
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
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
	
	
	
}

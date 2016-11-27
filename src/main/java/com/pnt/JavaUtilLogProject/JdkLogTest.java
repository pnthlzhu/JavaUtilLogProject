package com.pnt.JavaUtilLogProject;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * JDK14日志框架
 * @author hlzhu
 * @date 2016年11月25日
 */
public class JdkLogTest {

	/**
	 * 简单日志输出
	 * logger默认的级别是INFO，比INFO更低的日志将不显示。
	 * Logger的默认级别定义是在jre安装目录的lib下面。
	 * logger的名字是有层级关系的，这和log4j的控制方式完全一致。
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		/*
		 * API文档的原文
		 * 一般使用圆点分隔的层次命名空间来命名 Logger。
		 * Logger 名称可以是任意的字符串，但是它们一般应该基于被记录组件的包名或类名，
		 * 如 java.net 或 javax.swing。此外，可以创建“匿名”的 Logger，
		 * 其名称未存储在 Logger 命名空间中。
		 */
		Logger logger1 = Logger.getLogger("com");
		logger1.setLevel(Level.INFO);
		
		Logger logger2 = Logger.getLogger("com.pnt");
		logger2.setLevel(Level.WARNING);
		
		System.out.println(logger1 == logger2);
		
		logger1.info("hello jdk log");
		logger2.info("hello world");
		logger2.warning("hello coder");
	}
	
	/**
	 * Handler 对象从 Logger 中获取日志信息，并将这些信息导出。
	 * 例如，它可将这些信息写入控制台或文件中，也可以将这些信息发送到网络日志服务中，或将其转发到操作系统日志中。
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		Logger logger = Logger.getLogger("com");
		logger.setLevel(Level.CONFIG);
		
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		
		FileHandler fileHandler = new FileHandler("C:/testlog%g.log");
		fileHandler.setLevel(Level.CONFIG);
		
		logger.addHandler(consoleHandler);
		logger.addHandler(fileHandler);
		
		logger.config("I'm config message");
		logger.info("I'm info message");
		logger.warning("I'm warning message");
		logger.severe("I'm severe message");
	}
	
	/**
	 * Formatter 为格式化 LogRecords 提供支持。 
	 * 一般来说，每个日志记录 Handler 都有关联的 Formatter。
	 * Formatter 接受 LogRecord，并将它转换为一个字符串。 
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Logger logger = Logger.getLogger("com");
		logger.setLevel(Level.CONFIG);
		
		FileHandler fileHandler = new FileHandler("C:/testlog%g.log");
		fileHandler.setLevel(Level.CONFIG);
		fileHandler.setFormatter(new Formatter() {
			
			@Override
			public String format(LogRecord record) {
				return record.getLevel() + ":" + record.getMessage() + "\n";
			}
		});
		
		logger.addHandler(fileHandler);
		
		logger.config("I'm config message");
		logger.info("I'm info message");
		logger.warning("I'm warning message");
		logger.severe("I'm severe message");
	}
}

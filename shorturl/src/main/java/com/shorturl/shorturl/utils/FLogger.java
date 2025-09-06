package com.shorturl.shorturl.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FLogger {

	private static final String DEFAULT_SEPEARATOR = " - ";
	
	private static final String PATTERN1 = "{} - {} - {} - {} - {}";

	
	private static Logger logger;
	
	public static Logger getLogger(String loggerConstant) {
		try {
			if(logger == null) {
				logger = LogManager.getLogger(loggerConstant);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return logger;
	}

	private static StackTraceElement getCallerInfo() {
		return Thread.currentThread().getStackTrace()[3];
	}

	private static String getProcessId() {
		String jvmName = String.valueOf(java.lang.management.ManagementFactory.getRuntimeMXBean().getPid());

		return jvmName.split("@")[0];
	}

	private static String forwardMessage(String message) {
		StackTraceElement caller = getCallerInfo();

		String fullClassName = caller.getClassName();
		String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
		String methodName = caller.getMethodName();
		String thredId = String.valueOf(Thread.currentThread().threadId());
		String processId = getProcessId();
		
//		System.err.println(caller.getClassName());

		return className + DEFAULT_SEPEARATOR + methodName + DEFAULT_SEPEARATOR + "PID:" + processId
				+ DEFAULT_SEPEARATOR + "TID:" + thredId + DEFAULT_SEPEARATOR + message;

	}
	
	public static void debug(Logger logger, String message) {
		if(logger.isDebugEnabled()) {
			logger.debug(forwardMessage(message));
		}
	}
	
	public static void info(Logger logger, String message) {
		if(logger.isInfoEnabled()) {
			logger.info(forwardMessage(message));
		}
	}
	
	public static void warn(Logger logger, String message) {
		if(logger.isWarnEnabled()) {
			logger.warn(forwardMessage(message));
		}
	}
	
	public static void error(Logger logger, String message) {
		if(logger.isErrorEnabled()) {
			logger.error(forwardMessage(message));
		}
	}
	
	public static void error(Logger logger, String message, Throwable throwable) {
		if(logger.isErrorEnabled()) {
			logger.error(forwardMessage(message), throwable);
		}
	}
	
	public static void info(String loggerConstant, String className, String methodName, String messsage) {
		Logger logger = getLogger(loggerConstant);
		logger.error(PATTERN1, className.substring(className.lastIndexOf('.')+1), methodName, Thread.currentThread().hashCode(), getProcessId(), messsage);
	}
	
	public static void debug(String loggerConstant, String className, String methodName, String messsage) {
		Logger logger = getLogger(loggerConstant);
		logger.debug(PATTERN1, className.substring(className.lastIndexOf('.')+1), methodName, Thread.currentThread().hashCode(), getProcessId(), messsage);
	}
	
	public static void warn(String loggerConstant, String className, String methodName, String messsage) {
		Logger logger = getLogger(loggerConstant);
		logger.warn(PATTERN1, className.substring(className.lastIndexOf('.')+1), methodName, Thread.currentThread().hashCode(), getProcessId(), messsage);
	}
	
	public static void error(String loggerConstant, String className, String methodName, String messsage) {
		Logger logger = getLogger(loggerConstant);
		logger.error(PATTERN1, className.substring(className.lastIndexOf('.')+1), methodName, Thread.currentThread().hashCode(), getProcessId(), messsage);
	}
	
	public static void info(String loggerConstant, String className, String methodName, Exception e) {
		Logger logger = getLogger(loggerConstant);
		logger.error(PATTERN1, className.substring(className.lastIndexOf('.')+1), methodName, Thread.currentThread().hashCode(), getProcessId(), e.getMessage());
	}
	
	public static void debug(String loggerConstant, String className, String methodName, Exception e) {
		Logger logger = getLogger(loggerConstant);
		logger.debug(PATTERN1, className.substring(className.lastIndexOf('.')+1), methodName, Thread.currentThread().hashCode(), getProcessId(), e.getMessage());
	}
	
	public static void warn(String loggerConstant, String className, String methodName, Exception e) {
		Logger logger = getLogger(loggerConstant);
		logger.warn(PATTERN1, className.substring(className.lastIndexOf('.')+1), methodName, Thread.currentThread().hashCode(), getProcessId(), e.getMessage());
	}
	
	public static void error(String loggerConstant, String className, String methodName, Exception e) {
		Logger logger = getLogger(loggerConstant);
		logger.error(PATTERN1, className.substring(className.lastIndexOf('.')+1), methodName, Thread.currentThread().hashCode(), getProcessId(), e.getMessage());
	}
}

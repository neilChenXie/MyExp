package com.chen.util;

public class JedisUtilsException extends Exception {

	private static final long serialVersionUID = 131434353423L;

	public JedisUtilsException(String message) {
		super(message);
	}

	public JedisUtilsException(Throwable e) {
		super(e);
	}

	public JedisUtilsException(String message, Throwable cause) {
		super(message, cause);
	}
}

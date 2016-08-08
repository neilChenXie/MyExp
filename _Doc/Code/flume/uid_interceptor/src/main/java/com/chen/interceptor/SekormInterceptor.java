/**
 * 
 */
package com.chen.interceptor;

import java.util.List;
import java.util.Map;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chen.util.RedisUtils;
import com.chen.util.SerializeUtils;

/**
 * @describe 处理JSON日志：1. 过滤无用URL；2.提取UID；3.提取文章ID
 *
 * @author Chen Xie
 * @date Jun 16, 2016
 */
public class SekormInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(SekormInterceptor.class);

	private RedisUtils redisUtils;

	private String cookieField = "cookie_JSESSIONID";

	private String redisPrefix = "shiro_session:";

	// private RedisUtils getRedisUtils() {
	//
	// return redisUtils;
	// }
	private SekormInterceptor(String host, int port) {
		redisUtils = RedisUtils.getInstance();
		redisUtils.setHost(host);
		redisUtils.setPort(port);
	}

	@Override
	public void close() {

	}

	@Override
	public void initialize() {

	}

	@Override
	public Event intercept(Event event) {
		Map<String, String> headers = event.getHeaders();
		String body = new String(event.getBody());
		// String body = (String) SerializeUtils.deserialize(event.getBody());

		if (logger.isDebugEnabled()) {
			logger.debug("body: {}", body);
		}

		String cookieId = getCookie(body);

		String uid = getUID(cookieId);

		headers.put("uid", uid);

		if (logger.isDebugEnabled()) {
			logger.debug("Event: {}", event);
		}

		return event;
	}

	@Override
	public List<Event> intercept(List<Event> events) {
		for (Event event : events) {
			intercept(event);
		}
		return events;
	}

	private String getCookie(String body) {
		String cookieId = null;

		try {
			JSONObject bd = JSON.parseObject(body);
			cookieId = (String) bd.get(getCookieField());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cookieId;
	}

	/**
	 * @describe get uid from redis based on cookie
	 *
	 * @author neil_xie
	 * @date Jun 30, 2016
	 * @param
	 * @return
	 */
	private String getUID(String cookieId) {

		String uid = "unknown";

		if (cookieId == "-" || cookieId == null) {
			return uid;
		}

		Session session = null;
		try {
			byte[] key = redisPrefix.concat(cookieId).getBytes();
			byte[] value = redisUtils.get(key);
			session = (Session) SerializeUtils.deserialize(value);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (session != null) {
			uid = (String) session.getAttribute("uid");
		}

		return uid;
	}

	public String getCookieField() {
		return cookieField;
	}

	public void setCookieField(String cookieField) {
		this.cookieField = cookieField;
	}

	public static class Builder implements Interceptor.Builder {

		private String host = "127.0.0.1";
		private int port = 6379;
		private String password = null;

		@Override
		public void configure(Context context) {
			host = context.getString("host", host);
			port = context.getInteger("port", port);
			password = context.getString("password", password);
			if (logger.isDebugEnabled()) {
				logger.debug("url: {}, password: {}", host + ":" + port, password);
			}
		}

		@Override
		public Interceptor build() {
			return new SekormInterceptor(host, port);
		}
	}

}

/**
 * 
 */
package com.chen;

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
 * @describe
 *
 * @author Chen Xie
 * @date Jun 16, 2016
 */
public class SimpleInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(SimpleInterceptor.class);

	private RedisUtils redisUtils;

	private String cookieField = "cookie_JSESSIONID";

	private String redisPrefix = "shiro_session:";

	private RedisUtils getRedisUtils() {

		synchronized (SimpleInterceptor.class) {
			if (redisUtils == null) {
				redisUtils = new RedisUtils();
			}
		}

		return redisUtils;
	}

	@Override
	public void close() {

	}

	@Override
	public void initialize() {

	}

	@Override
	public Event intercept(Event event) {
		logger.debug("!!!!enter intercept!!!!!");
		Map<String, String> headers = event.getHeaders();
		// String body = (String) SerializeUtils.deserialize(event.getBody());
		 String body = new String(event.getBody());
		if (logger.isDebugEnabled()) {
			logger.debug("body: {}", body);
		}

		String cookieId = null;

		try {
			JSONObject bd = JSON.parseObject(body);
			cookieId = (String) bd.get(getCookieField());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String uid = getUID(cookieId);

		logger.debug("uid: {}", uid);
		headers.put("uid", uid);

		return event;
	}

	@Override
	public List<Event> intercept(List<Event> events) {
		logger.info("!!!!enter intercept list!!!!");
		for (Event event : events) {
			intercept(event);
		}
		return events;
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

		if (cookieId == "-") {
			return uid;
		}
		
		RedisUtils redisUtils = getRedisUtils();
		redisUtils.setHost("172.16.2.51");
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

		@Override
		public void configure(Context context) {

		}

		@Override
		public Interceptor build() {
			return new SimpleInterceptor();
		}
	}

}

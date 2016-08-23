package com.chen;

import org.apache.flume.Event;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.interceptor.Interceptor;
import org.junit.Test;

import com.chen.util.SerializeUtils;

public class SimpleInterceptorTest {

//	@Test
//	public void testBasic() throws Exception {
//
//		// Context context = new Context();
//		// context.put(Constants.DELIMITER, "|");
//		// context.put(Constants.INDEX, "0");
//		// context.put(Constants.FORMAT, dateFormat);
//		// context.put(Constants.PRESERVE, "false");
//
//		Interceptor.Builder builder = new SimpleInterceptor.Builder();
//		// builder.configure(context);
//		Interceptor interceptor = builder.build();
//
//		String msg = "{ \"remote_addr\": \"127.0.0.1\"," + "\"remote_user\": \"-\","
//				+ "\"time_local\": \"29/Jun/2016:14:53:58 +0800\","
//				+ "\"request_method\": \"GET\", \"request_uri\": \"/st/task/addNew\"," + "\"request_body\": \"-\","
//				+ "\"cookie_JSESSIONID\": \"ef2ed021-44d5-4a09-92ee-6bafaf4f9dd6\"," + "\"status\": \"200\","
//				+ "\"body_bytes_sent\": \"23\"," + "\"http_referrer\": \"-\","
//				+ "\"http_user_agent\": \"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36\","
//				+ "\"http_x_forwarded_for\": \"-\"}";
//
//		Event event = EventBuilder.withBody(SerializeUtils.serialize(msg));
//
//		event = interceptor.intercept(event);
//	}
}

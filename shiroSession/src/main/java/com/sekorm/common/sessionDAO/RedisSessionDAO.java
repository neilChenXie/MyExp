package com.sekorm.common.sessionDAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisSessionDAO extends AbstractSessionDAO {

	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

	private String keyPrefix = "shiro_session:";

	private RedisUtils redisUtils;

	int timeout = 300; // 默认从不失效

	private ConcurrentMap<Serializable, Session> sessionMap;

	public RedisSessionDAO() {
		this.sessionMap = new ConcurrentHashMap<Serializable, Session>();
	}

	/**
	 * @Override
	 * @describe 通过generateSessionId得到新的ID，并与session组合存入redis
	 * 
	 * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.
	 *      shiro.session.Session)
	 */
	@Override
	protected Serializable doCreate(Session session) {

		// 获取uuid的sessionId
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		// 存入
		saveSession(session, getTimeout() * 1000);

		return sessionId;
	}

	/**
	 * @Override
	 * @describe 更新session信息
	 * 
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#update(org.apache.shiro.
	 *      session.Session)
	 */
	@Override
	public void update(Session session) {
		saveSession(session, getTimeout() * 1000);
	}

	/**
	 * @describe 向redis中写入session
	 *
	 * @author neil_xie
	 * @date Jun 27, 2016
	 * @param
	 * @return
	 */
	private void saveSession(Session session, long timeout) {

		if (!validSession(session)) {
			return;
		}

		byte[] key = getRedisKey(session.getId());
		byte[] value = SerializeUtils.serialize(session);

		// session 超时
		session.setTimeout(timeout);

		try {
			redisUtils.set(key, value, redisUtils.getExpire());
			// 存入redis
		} catch (JedisUtilsException e) {
			logger.error("Redis Error： {}", e.getMessage());
		} finally {
			// 存入内存
			sessionMap.putIfAbsent(session.getId(), session);
		}

	}

	/**
	 * @Override
	 * @describe 读取session信息
	 * 
	 * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doReadSession(java.io
	 *      .Serializable)
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {

		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}

		Session redisSession = null;
		Session memorySession = sessionMap.get(sessionId);

		try {
			// 从Redis获得session
			redisSession = (Session) SerializeUtils.deserialize(redisUtils.get(getRedisKey(sessionId)));
		} catch (JedisUtilsException e) {
			logger.error("Redis Error： {}", e.getMessage());
		}

		// Redis挂掉or启动恢复：内存有，Redis没有，写入Redis，返回
		if (redisSession == null && memorySession != null) {
			// 从内存得到备份session,并恢复Redis的值
			saveSession(memorySession, getTimeout() * 1000);
			redisSession = memorySession;
		}

		// 服务器启动：Redis与内存不同时，以Redis为准
		if (redisSession != memorySession) {
			saveSession(redisSession, getTimeout() * 1000);
		}

		return redisSession;
	}

	/**
	 * @Override
	 * @describe 删除Session 如果内存删除了，redis未删除，但是相应的securityManager会被删除，下次不会出现登录被恢复
	 * 
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#delete(org.apache.shiro.
	 *      session.Session)
	 */
	@Override
	public void delete(Session session) {

		if (!validSession(session)) {
			return;
		}

		saveSession(session, 0);
		// try {
		// // 从Redis删除session
		// redisUtils.del(getRedisKey(session.getId()));
		// } catch (JedisUtilsException e) {
		// logger.error("Redis Error： {}", e.getMessage());
		// } finally {
		// // 从内存删除session
		// sessionMap.remove(getRedisKey(session.getId()));
		// }
	}

	/**
	 * @Override
	 * @describe 获取所有的有效Session
	 * 
	 * @see org.apache.shiro.session.mgt.eis.SessionDAO#getActiveSessions()
	 */
	@Override
	public Collection<Session> getActiveSessions() {

		Collection<Session> sessions = new HashSet<Session>();

		try {
			// 从Redis读取
			Set<byte[]> keys = redisUtils.keys(this.keyPrefix + "*");
			if (keys != null && keys.size() > 0) {
				for (byte[] key : keys) {
					Session s = (Session) SerializeUtils.deserialize(redisUtils.get(key));
					sessions.add(s);
				}
			}
		} catch (JedisUtilsException e) {
			// 从内存读取
			logger.error("Redis Error： {}", e.getMessage());
			Collection<Session> values = sessionMap.values();
			sessions = values;
		}

		// 返回
		if (CollectionUtils.isEmpty(sessions)) {
			return Collections.emptySet();
		} else {
			return Collections.unmodifiableCollection(sessions);
		}
	}

	/**
	 * @describe 验证是否为有效session
	 *
	 * @author neil_xie
	 * @date Jun 27, 2016
	 * @param
	 * @return
	 */
	private boolean validSession(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session or session id 为空");
			return false;
		}
		return true;
	}

	/**
	 * @describe 组装存入redis的key
	 *
	 * @author neil_xie
	 * @date Jun 27, 2016
	 * @param
	 * @return
	 */
	private byte[] getRedisKey(Serializable sessionId) {
		String preKey = this.keyPrefix + sessionId;
		return preKey.getBytes();
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	public void setRedisUtils(RedisUtils redisUtils) {
		this.redisUtils = redisUtils;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
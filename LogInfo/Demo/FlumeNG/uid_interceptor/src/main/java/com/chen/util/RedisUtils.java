package com.chen.util;

import java.io.IOException;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @describe Redis 工具了 异常抛出 RedisUtilsException
 *
 * @author chen
 * @date Jun 27, 2016
 */
public class RedisUtils {

	// jedis pool properties, default value
	private JedisPoolConfig jedisPoolConfig = null;
	private String host = "127.0.0.1";
	private int port = 6379;
	private int timeout = 2000;
	private String password = null;

	// jedis properties
	private int expire = 300;

	private JedisPool jedisPool = null;

	public RedisUtils() {
		// 生成默认设置
		setJedisPoolConfig(new JedisPoolConfig());
	}

	/**
	 * @describe 初始化redisPool
	 *
	 * @author
	 * @date Jun 27, 2016
	 * @param
	 * @return
	 */
	private JedisPool getPool() {
		if (jedisPool == null) {
			// 在第一次使用时初始化
			jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
		}
		return jedisPool;
	}

	/**
	 * @describe 关闭一个jedis连接
	 *
	 * @author chen
	 * @date Jun 27, 2016
	 * @param
	 * @return
	 */
	private void returnJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	public byte[] get(byte[] key) throws JedisUtilsException {
		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			throw new JedisUtilsException("redis连接错误", e);
		} finally {
			returnJedis(jedis);
		}
		return value;
	}

	/**
	 * @describe 创建redis k-v,并设置全局的expire值
	 *
	 * @author chen
	 * @date Jun 27, 2016
	 * @param
	 * @return
	 * @throws IOException
	 */
	public void set(byte[] key, byte[] value) throws JedisUtilsException {
		set(key, value, getExpire());
	}

	/**
	 * @describe 创建redis k-v,并设置传入的expire值
	 *
	 * @author neil_xie
	 * @date Jun 27, 2016
	 * @param key,
	 *            value, expire
	 * @return
	 * @throws IOException
	 * @throws JedisUtilsException
	 */
	public void set(byte[] key, byte[] value, int expire) throws JedisUtilsException {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			jedis.set(key, value);
			if (expire != 0) {
				jedis.expire(key, expire);
			}
		} catch (Exception e) {
			throw new JedisUtilsException("redis连接错误", e);
		} finally {
			returnJedis(jedis);
		}
	}

	/**
	 * @describe 删除一个k-v
	 *
	 * @author neil_xie
	 * @date Jun 27, 2016
	 * @param key
	 * @return
	 */
	public void del(byte[] key) throws JedisUtilsException {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			jedis.del(key);
		} catch (Exception e) {
			throw new JedisUtilsException("redis连接错误", e);
		} finally {
			returnJedis(jedis);
		}
	}

	/**
	 * @describe 查看所有符合正则的Keys
	 *
	 * @author neil_xie
	 * @date Jun 27, 2016
	 * @param pattern
	 * @return
	 * @throws JedisUtilsException
	 */
	public Set<byte[]> keys(String pattern) throws JedisUtilsException {
		Set<byte[]> keys = null;
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			keys = jedis.keys(pattern.getBytes());
		} catch (Exception e) {
			throw new JedisUtilsException("redis连接错误", e);
		} finally {
			returnJedis(jedis);
		}
		return keys;
	}

	/**
	 * @describe flush redis
	 *
	 * @author neil_xie
	 * @date Jun 27, 2016
	 * @param
	 * @return
	 * @throws JedisUtilsException
	 */
	public void flunshDB() throws JedisUtilsException {
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			jedis.flushDB();
		} catch (Exception e) {
			throw new JedisUtilsException("redis连接错误", e);
		} finally {
			returnJedis(jedis);
		}
	}

	/**
	 * @describe 查看DB大小
	 *
	 * @author neil_xie
	 * @date Jun 27, 2016
	 * @param
	 * @return
	 * @throws JedisUtilsException
	 */
	public long dbSize() throws JedisUtilsException {
		Long dbSize = 0L;
		Jedis jedis = null;
		try {
			jedis = getPool().getResource();
			dbSize = jedis.dbSize();
		} catch (Exception e) {
			throw new JedisUtilsException("redis连接错误", e);
		} finally {
			returnJedis(jedis);
		}
		return dbSize;
	}

	public JedisPoolConfig getJedisPoolConfig() {
		return jedisPoolConfig;
	}

	public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
		this.jedisPoolConfig = jedisPoolConfig;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}

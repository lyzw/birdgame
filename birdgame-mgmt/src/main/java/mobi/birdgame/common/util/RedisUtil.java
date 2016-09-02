package mobi.birdgame.common.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public final class RedisUtil {

    private static Log logger = LogFactory.getLog(RedisUtil.class);
    private static String ADDR = "192.168.0.100";
    private static int PORT = 6379;
    private static String AUTH = "admin";
    private static int MAX_IDLE = 200;
    private static int MAX_TOTAL = 200;
    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;
    private static int DB_ID;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("config");
            ADDR = bundle.getString("redis.address");
            PORT = Integer.parseInt(bundle.getString("redis.port"));
            TIMEOUT = Integer.parseInt(bundle.getString("redis.timeout"));
            AUTH = bundle.getString("redis.auth");
            DB_ID = Integer.parseInt(bundle.getString("redis.db"));

            MAX_IDLE = Integer.parseInt(bundle.getString("redis.maxidle"));
            TEST_ON_BORROW = Boolean.parseBoolean(bundle.getString("redis.testonborrow"));
            MAX_TOTAL = Integer.parseInt(bundle.getString("redis.maxtotal"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化Redis连接池
     */
    private static void initialPool() {
        try {

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(MAX_IDLE);
            config.setTestOnBorrow(TEST_ON_BORROW);
            config.setMaxTotal(MAX_TOTAL);
            if (AUTH == null || "".equals(AUTH)) {
                jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
            } else {
                jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
            }
        } catch (Exception e2) {
            logger.error("Create JedisPool error : " + e2);
        }
    }


    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {
        if (jedisPool == null) {
            poolInit();
        }
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            logger.error("Get jedis error : " + e);
        } finally {
            returnResource(jedis);
        }
        return jedis;
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (jedisPool == null) {
            initialPool();
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        logger.debug("return redis resource ......");
        if (jedis != null && jedisPool != null) {
            jedisPool.returnResourceObject(jedis);
        }
    }
}

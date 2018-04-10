package org.slsale.common;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author takooya
 * @Description
 * @Date:created in 14:03 2018/4/10
 */
public class RedisAPI {
    public JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public boolean set(String key,String value){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            String set = jedis.set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            returnResource(jedisPool,jedis);
        }
    }
    public boolean exicts(String key){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            returnResource(jedisPool,jedis);
        }
    }
    public String get(String key){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            returnResource(jedisPool,jedis);
        }
    }
    public static void returnResource(JedisPool jedisPool,Jedis jedis){
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }
}

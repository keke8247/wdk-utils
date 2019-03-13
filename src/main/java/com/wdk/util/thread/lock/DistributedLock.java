package com.wdk.util.thread.lock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @Description
 * 基于Redis 实现分布式锁 暂时不考虑Redis集群
 *
 * 分布式锁特性:
 * 1:互斥性,同一时间只有一个Client持有锁.
 * 2:不能发生死锁. 一个Client获取锁后 如果因为某种原因没有正确释放锁. 也不能影响其他Client获取锁.
 * 3:具有容错性. 只要大部分Redis节点正常运行.客户端就可以正常加锁和解锁.
 * 4:释放锁的客户端必须是获取锁的客户端.
 *
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/3/13 16:54
 * @Since version 1.0.0
 */
public class DistributedLock {

    private static final String STATUS = "OK";
    private static final String SET_IF_NOT_EXISTS = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    private static Jedis jedis;

    public DistributedLock(){
        //初始化jedis
        jedis = new Jedis();

    }
    /**
     * @Description:
     * 由于不考虑Redis集群环境. 该方法满足了以上分布式锁的3个特性.
     * 1:通过 SET_IF_NOT_EXISTS参数 满足互斥性.当key不存在 加锁.当key存在 不做操作.
     * 2:通过 SET_WITH_EXPIRE_TIME expireTime 设置过期时间.满足不能发生死锁的特性. 一段时间过期后会释放锁
     * 3:通过 存储lockKey的值 requestId 在释放锁的时候 判断是不是同一个客户端在操作.满足 谁加锁谁释放锁.
     * @Param
     * @return
    */
    public static boolean tryGetDistributedLock(String lockKey,String requestId,Long expireTime){
        String result = jedis.set(lockKey,requestId,SET_IF_NOT_EXISTS,SET_WITH_EXPIRE_TIME,expireTime);
        if(STATUS.equals(result)){
            return true;
        }
        return false;
    }

    /**
     * @Description:
     * 通过lua脚本
     * eval命令执行Lua代码的时候，Lua代码将被当成一个命令去执行，并且直到eval命令执行完成，Redis才会执行其他命令。
     * 是原子性的.Atomicity
     * @Param
     * @return
    */
    public static boolean releaseDistributeLock(String lockKey,String requestId){
        //参数KEYS[1]赋值为 lockKey
        //参数ARGV[1]赋值为 requestId
        //lua 脚本的意思是: 如果redis调用get(KEYS[1])获取到的value == requestId 则认为是加锁的客户端在解锁. 则调用del(KEYS[1])操作 删除锁.
        //否则不做处理

        String luaStr = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

        Object result = jedis.eval(luaStr, Collections.singletonList(lockKey),Collections.singletonList(requestId));

        if(RELEASE_SUCCESS == result){
            return true;
        }
        return false;
    }
}

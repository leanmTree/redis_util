package com.redis.util.redis_util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author hengtao.wu
 * @Date 2020/9/28 9:50
 **/
@Configuration
public class RedisUtil implements RedisCache{


    private static Pattern PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * setKV值，无过期时间
     *
     * @param key
     * @param value
     */
    @Override
    public <T> Boolean set(String key, T value) {
        if(StringUtils.isEmpty(key)) {
            return false;
        }
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    /**
     * 获取K的V值
     *
     * @param key
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除某个KEY值
     *
     * @param key
     */
    @Override
    public Boolean del(String key) {
        if(StringUtils.isEmpty(key)) {
            return false;
        }
        return redisTemplate.delete(key);
    }

    /**
     * 给某个key值拼接字符串
     *
     * @param key
     * @param value
     * @return 返回当前key的长度
     */
    @Override
    public Integer append(String key, String value) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().append(key, value);
    }

    /**
     * @param key
     * @return 返回当前key的长度
     * strlen指令
     */
    @Override
    public Long lenth(String key) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().size(key);
    }

    /**
     * 给当前KEY值+1
     *
     * @param key
     * @return 返回增加后的value值
     */
    @Override
    public Long incr(String key) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 给当前KEY值增加指定步长
     *
     * @param key
     * @param step
     * @return 返回增加后的value值
     */
    @Override
    public Long incrBy(String key, Integer step) {
        if(StringUtils.isEmpty(key) || step < 1) {
            return null;
        }
        return redisTemplate.opsForValue().increment(key, step);
    }

    /**
     * 给当前KEY值-1
     *
     * @param key
     * @return 返回减少后的value值
     */
    @Override
    public Long decr(String key) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 给当前KEY值减少指定步长
     *
     * @param key
     * @param step
     * @return 返回减少后的value值
     */
    @Override
    public Long decrBy(String key, Integer step) {
        if(StringUtils.isEmpty(key) || step < 1) {
            return null;
        }
        return redisTemplate.opsForValue().decrement(key, step);
    }

    /**
     * 截取指定位置字符
     * getrange指令
     *
     * @param key
     * @param start
     * @param end
     */
    @Override
    public String subString(String key, long start, long end) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 将该value值，从指定位置开始替换为value值
     * setrange指令
     * @param key
     * @param start 开始替换位置
     * @param value 替换值
     * @return 返回value值，总长度不变
     */
    @Override
    public Boolean replace(String key, long start, String value) {
        if(StringUtils.isEmpty(key)) {
            return false;
        }
        redisTemplate.opsForValue().set(key, value, start);
        return true;
    }

    /**
     * 设置KV值，并设置过期时间
     *
     * @param key
     * @param t
     * @param timeOut
     * @param timeUnit
     */
    @Override
    public <T> void setExpire(String key, T t, Long timeOut, TimeUnit timeUnit) {
        if(StringUtils.isEmpty(key) || null == timeOut) {
            return;
        }
        if(null == timeUnit) {
            timeUnit = TimeUnit.SECONDS;
        }
        redisTemplate.opsForValue().set(key, t, timeOut, timeUnit);
    }

    /**
     * 设置值，当不存在时
     *
     * @param key
     * @param t
     * @return 存在返回false，不存在则创建返回true
     */
    @Override
    public <T> Boolean setnx(String key, T t) {
        if(StringUtils.isEmpty(key)) {
            return false;
        }
        return redisTemplate.opsForValue().setIfAbsent(key, t);
    }

    @Override
    public <T> Boolean setnx(String key, T t, Long timeOut, TimeUnit timeUnit) {
        if(StringUtils.isEmpty(key)) {
            return false;
        }
        return redisTemplate.opsForValue().setIfAbsent(key, t, timeOut, timeUnit);
    }

    /**
     * 如果不存在值，返回null，并设置新值
     * 如果存在，返回原来值，并设置新的值。可用来更新操作。
     *
     * @param key
     * @param t
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getSet(String key, T t) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return (T) redisTemplate.opsForValue().getAndSet(key, t);
    }

    /**
     * 存放list值，从右至左
     *
     * @param key
     * @param t
     * @return 返回该list当前的长度
     */
    @Override
    public <T> Long lPush(String key, T t) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().leftPush(key, t);
    }

    /**
     * 将一个非空的list集合从右至左存放至list中
     *
     * @param key
     * @param t
     * @return 返回该list当前的长度
     */
    @Override
    public <T> Long lPushAll(String key, List<T> t) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().leftPushAll(key, t);
    }

    /**
     * 存放list值，从左至右
     *
     * @param key
     * @param t
     * @return 返回该list当前的长度
     */
    @Override
    public <T> Long rPush(String key, T t) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().rightPush(key, t);
    }

    /**
     * 将一个非空的list集合从左至右存放至list中
     *
     * @param key
     * @param t
     * @return 返回该list当前的长度
     */
    @Override
    public <T> Long rPushAll(String key, List<T> t) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().rightPushAll(key, t);
    }

    /**
     * 获取全部list的值
     * lrange (list, 0, -1)
     *
     * @param key
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(String key) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return (List<T>) redisTemplate.opsForList().range(key, 0,  -1);
    }

    /**
     * 获取指定下标list的值
     *
     * @param key
     * @param start 开始下标
     * @param end   结束下边
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> lRange(String key, long start, long end) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return (List<T>) redisTemplate.opsForList().range(key, start,  end);
    }

    /**
     * 将list中的最左边元素弹出，并返回弹出值
     *
     * @param key
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T lPop(String key) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return (T) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 将list中的最右边元素弹出，并返回弹出值
     *
     * @param key
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T rPop(String key) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 获取该list指定下标的元素，从0开开始
     *
     * @param key
     * @param index
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T index(String key, long index) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return (T) redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取该list的长度
     *
     * @param key
     */
    @Override
    public Long size(String key) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 移动操作。将key中的最后一个(rpop)移动到(lpush) destinationKey中去，destinationKey中就是key被移除的最后一个值
     *
     * @param key            原list
     * @param destinationKey 目标list
     * @return 返回移动的值
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T rPopLpush(String key, String destinationKey) {
        if(StringUtils.isEmpty(key) || StringUtils.isEmpty(destinationKey)) {
            return null;
        }
        return (T) redisTemplate.opsForList().rightPopAndLeftPush(key, destinationKey);
    }

    /**
     * 将列表中已经存在的指定下标的值更新为另一个值，如果list不存在/或者下标越界，则报错。
     *
     * @param key   目标list
     * @param index 更新的下标
     * @param t     更新的值
     */
    @Override
    public <T> void lSet(String key, long index, T t) {
        if(StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.opsForList().set(key, index, t);
    }

    /**
     * 在指定的元素前面插入一个值
     *
     * @param key
     * @param pivot 指定值
     * @param value 要插入的值
     * @return 返回当前list的长度
     */
    @Override
    public <T> Long insertBefor(String key, T pivot, T value) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T hashGet(String realKey) {
        if (realKey == null) {
            return null;
        }
        return (T) redisTemplate.opsForHash().entries(realKey);
    }

    /**
     * 在指定的元素后面插入一个值
     *
     * @param key
     * @param pivot 指定值
     * @param value 要插入的值
     * @return 返回当前list的长度
     */
    @Override
    public <T> Long insertAfter(String key, T pivot, T value) {
        if(StringUtils.isEmpty(key)) {
            return null;
        }
        return redisTemplate.opsForList().rightPush(key, pivot, value);
    }

    public static boolean isInteger(String str) {
        return PATTERN.matcher(str).matches();
    }
}

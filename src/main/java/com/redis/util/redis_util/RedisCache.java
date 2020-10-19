package com.redis.util.redis_util;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author hengtao.wu
 * Redis缓存方法接口----五大基本类型常用API
 * @Date 2020/9/28 9:54
 **/
public interface RedisCache {

    /*
    ###########################String类型方法###############################
     */

    /**
     *setKV值，无过期时间
     */
    <T> Boolean set(String key, T value);

    /**
     * 获取K的V值
     */
    <T> T get(String key);


    /**
     *删除某个KEY值
     */
    Boolean del(String key);


    /**
     *给某个key值拼接字符串
     * @return 返回当前key的长度
     */
    Integer append(String key, String value);


    /**
     * @return 返回当前key的长度
     * strlen指令
     */
    Long lenth(String key);


    /**
     * 给当前KEY值+1
     * @return  返回增加后的value值
     */
    Long incr(String key);


    /**
     * 给当前KEY值增加指定步长
     * @return 返回增加后的value值
     */
    Long incrBy(String key, Integer step);


    /**
     * 给当前KEY值-1
     * @return  返回减少后的value值
     */
    Long decr(String key);


    /**
     * 给当前KEY值减少指定步长
     * @return 返回减少后的value值
     */
    Long decrBy(String key, Integer step);

    /**
     * 截取指定位置字符
     * getrange指令
     */
    String subString(String key, long start, long end);


    /**
     * 将该value值，从指定位置开始替换为value值
     * setrange指令
     * @param start 开始替换位置
     * @param value 替换值
     * @return 返回value值，总长度不变
     */
    Boolean replace(String key, long start, String value);


    /**
     * 设置KV值，并设置过期时间
     */
    <T> void setExpire(String key, T t, Long timeOut, TimeUnit timeUnit);


    /**
     * 设置值，当不存在时
     * @return 存在返回false，不存在则创建返回true
     */
    <T> Boolean setnx(String key, T t);

    <T> Boolean setnx(String key, T t, Long timeOut, TimeUnit timeUnit);

    /**
     * 如果不存在值，返回null，并设置新值
     * 如果存在，返回原来值，并设置新的值。可用来更新操作。
     */
    <T> T getSet(String key, T t);



    /*
    List类型方法
    作为栈、队列、阻塞队列来使用。list中的值是可以重复的,属于双端队列，底层是链表实现
     */

    /**
     * 存放list值，从右至左
     * @return 返回该list当前的长度
     */
    <T> Long lPush(String key, T t);


    /**
     * 将一个非空的list集合从右至左存放至list中
     * @return 返回该list当前的长度
     */
    <T> Long lPushAll(String key, List<T> t);


    /**
     * 存放list值，从左至右
     * @return 返回该list当前的长度
     */
    <T> Long rPush(String key, T t);


    /**
     * 将一个非空的list集合从左至右存放至list中
     * @return 返回该list当前的长度
     */
    <T> Long rPushAll(String key, List<T> t);


    /**
     * 获取全部list的值
     * lrange (list, 0, -1)
     */
    <T> List<T> getAll(String key);


    /**
     * 获取指定下标list的值
     * @param start 开始下标
     * @param end 结束下边
     */
    <T> List<T> lRange(String key, long start, long end);

    /**
     * 将list中的最左边元素弹出，并返回弹出值
     */
    <T> T lPop(String key);

    /**
     * 将list中的最右边元素弹出，并返回弹出值
     */
    <T> T rPop(String key);



    /**
     * 获取该list指定下标的元素，从0开开始
     */
    <T> T index(String key, long index);


    /**
     *获取该list的长度
     */
    Long size(String key);

    /**
     * 移动操作。将key中的最后一个(rpop)移动到(lpush) destinationKey中去，destinationKey中就是key被移除的最后一个值
     * @param key 原list
     * @param destinationKey 目标list
     * @return 返回移动的值
     */
    <T> T rPopLpush(String key, String destinationKey);


    /**
     * 将列表中已经存在的指定下标的值更新为另一个值，如果list不存在/或者下标越界，则报错。
     * @param key 目标list
     * @param index 更新的下标
     * @param t 更新的值
     */
    <T> void lSet(String key, long index, T t);


    /**
     * 在指定的元素前面插入一个值
     * @param pivot 指定值
     * @param value 要插入的值
     * @return 返回当前list的长度
     */
    <T> Long insertBefor(String key, T pivot, T value);


    /**
     * 在指定的元素后面插入一个值
     * @param pivot 指定值
     * @param value 要插入的值
     * @return 返回当前list的长度
     */
    <T> Long insertAfter(String key, T pivot, T value);


    /*
    Set类型方法：set集合中的元素是不可重复的，并且是无序的
     */




    /*
    String类型方法
     */


    /*
    String类型方法
     */

    /*
    String类型方法
     */

    /*
    String类型方法
     */

    /*
    String类型方法
     */

    /*
    String类型方法
     */

}

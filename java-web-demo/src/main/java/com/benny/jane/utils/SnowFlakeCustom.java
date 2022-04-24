package com.benny.jane.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SnowFlakeCustom {

    /**
     * 开始时间截 (1970-01-01)
     */
    private final long epoch = 0L;
    /**
     * 机器id所占的位数:0~15
     */
    private final long workerIdBits = 4L;

    /**
     * 支持的最大机器id，结果是15 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 15L;
    /**
     * 机器ID向左移15位
     */
    private final long workerIdShift = sequenceBits;
    /**
     * 时间截向左移19位(4+15)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits;
    /**
     * 生成序列的掩码，这里为32767 (1111111111111=32767)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 工作机器ID(0~15)
     */
    private long workerId;

    /**
     * 毫秒内序列(0~32767)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    public SnowFlakeCustom(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - epoch) << timestampLeftShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒 -》 秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis() / 1000L;
    }

    public static void parseId(long id) {
        long second = id >>> 19;
        System.err.println("分布式id-" + id + "生成的时间是：" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date(second * 1000)));
    }

    public static void main(String[] args) {
        SnowFlakeCustom idWorker = new SnowFlakeCustom(0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
            parseId(id);
        }
    }
}

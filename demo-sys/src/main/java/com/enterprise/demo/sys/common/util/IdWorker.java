package com.enterprise.demo.sys.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.SocketException;
import java.util.Set;

/**
 * Snowflake
 */
@Component("idWorker")
public class IdWorker {

    private static final Logger log = LoggerFactory.getLogger(IdWorker.class);

    private static final long twepoch = 1288834974657L;
    private static final long workerIdBits = 5L;
    private static final long datacenterIdBits = 5L;
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private static final long sequenceBits = 12L;
    private static final long workerIdShift = sequenceBits;
    private static final long datacenterIdShift = sequenceBits + workerIdBits;
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static long workerId;
    private static long datacenterId;
    private static long sequence = 0L;
    private static long lastTimestamp = -1L;

    /**
     * Instantiates a new Id worker.
     */
    public IdWorker() {
        try {
            String ip = IpUtils.getRealIp();
            if (StringUtils.isEmpty(ip)) {
                throw new RuntimeException("IdWorker get ip is empty");
            }
            this.workerId = this.datacenterId = Math.abs(ip.hashCode() % 31);
            log.info(
                    String.format("ip:%s,workerId:%s,datacenterId；%s", ip, workerId, datacenterId));
        } catch (SocketException e) {
            log.error("init error,error:{}", e);
            throw new RuntimeException("IdWorker init error");
        }
    }

    /**
     * Instantiates a new Id worker.
     *
     * @param workerId     the worker id
     * @param datacenterId the datacenter id
     */
    public IdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String
                    .format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * Next id long.
     *
     * @return the long
     */
    public static synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    /**
     * Til next millis long.
     *
     * @param lastTimestamp the last timestamp
     * @return the long
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * Time gen long.
     *
     * @return the long
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * test
     */
    static class IdWorkThread implements Runnable {
        private Set<Long> set;

        /**
         * Instantiates a new Id work thread.
         *
         * @param set the set
         */
        public IdWorkThread(Set<Long> set) {
            this.set = set;
        }

        @Override
        public void run() {
            while (true) {
                long id = IdWorker.nextId();
                System.out.println(Thread.currentThread().getName() + ":" + id);
                if (!set.add(id)) {
                    System.out.println("duplicate:" + id);
                }
            }
        }
    }
}

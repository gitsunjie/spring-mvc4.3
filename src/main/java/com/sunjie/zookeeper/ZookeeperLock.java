package com.sunjie.zookeeper;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperLock{

	private Logger logger = LoggerFactory.getLogger(ZookeeperLock.class);
	
    // zookeeper客户端
    private final CuratorFramework client;
    
    // curator重入锁
    private final InterProcessMutex lock;
    
    /**
     * 创建zookeeper锁，利用curator实现
     * @param connectString
     * @param rootPath
     */
    public ZookeeperLock(String connectString ,String rootPath){
    	this(1000, 1, 5000, 5000, connectString,  rootPath);
    }
    
    /**
     * 创建zookeeper锁，利用curator实现
     * @param baseSleepTimeMs 等待该时间后开始重试
     * @param maxRetries 重试次数
     * @param connectionTimeoutMs 连接超时时间
     * @param sessionTimeoutMs session超时时间
     * @param connectString zookeeper地址ip
     */
    public ZookeeperLock(int baseSleepTimeMs, int maxRetries, int connectionTimeoutMs, int sessionTimeoutMs,String connectString, String rootPath){
    	// 重试策略
        RetryPolicy policy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
    	// 创建zookeeper客户端连接
        client = CuratorFrameworkFactory
        		.builder()
        		.connectString(connectString)
        		.connectionTimeoutMs(connectionTimeoutMs)
        		.sessionTimeoutMs(sessionTimeoutMs)
        		.retryPolicy(policy)
        		.build();
        // 启动会话
        client.start();
        // 创建可重入锁
        this.lock = new InterProcessMutex(client, rootPath);
    }
    
    
    /**
     * 获取锁，获取失败阻塞
     * @param rootPath
     * @return
     * @throws Exception 
     */
    public void blockAcquire() throws Exception {
    	logger.info("@@@@尝试获取zookeeper锁，如果没有获取到锁则会一直阻塞，直到成功获取。。。");
		lock.acquire();
		logger.info("@@@@成功获取zookeeper锁");
    }
    
    /**
     * 获取锁，制定时间内获取失败返回false
     * @param rootPath
     * @return
     * @throws Exception 
     */
    public boolean unBlockAcquire(long time, TimeUnit unit) throws Exception{
    	logger.info("@@@@尝试获取zookeeper锁，如果没有获取，超过指定时间回返回false");
    	boolean falg = lock.acquire(time,unit);
    	if(falg){
    		logger.info("@@@@成功获取zookeeper锁");
    	}else{
    		logger.warn("@@@@获取zookeeper锁失败");
    	}
		return falg;
		
    }
    
    /**
     * 释放zookeeper锁
     * @throws Exception
     */
    public void release() throws Exception{
    	logger.info("@@@@释放zookeeper锁");
    	lock.release();
    	logger.info("@@@@成功释放zookeeper锁");
    }
}

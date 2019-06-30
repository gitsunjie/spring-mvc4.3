package com.sunjie.zookeeper;



public class ZookeeperInition {

	private void init() throws Exception{
		ZookeeperLock lock = new ZookeeperLock("106.15.235.209:2181,106.15.235.209:2183","/TestLocks");
		lock.blockAcquire();
		
	}
}

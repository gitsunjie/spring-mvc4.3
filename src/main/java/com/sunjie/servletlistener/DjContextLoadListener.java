package com.sunjie.servletlistener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class DjContextLoadListener extends ContextLoaderListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
//		System.out.println("ContextLoaderListener初始化之前启动。。。");
//		ZookeeperLock lock = new ZookeeperLock("106.15.235.209:2181，106.15.235.209:2182,106.15.235.209:2183","/Locks");
//		try {
//			lock.lock();
//			super.contextInitialized(event);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}

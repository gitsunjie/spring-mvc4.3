package com.sunjie.test;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.ServletContextAware;



public class StartupListener implements ApplicationContextAware, ServletContextAware,
InitializingBean, ApplicationListener<ContextRefreshedEvent> {
	
	
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        System.out.println("1 => StartupListener.setApplicationContext");
    }
 
    public void setServletContext(ServletContext context) {
        System.out.println("2 => StartupListener.setServletContext");
    }
 
    public void afterPropertiesSet() throws Exception {
        System.out.println("3 => StartupListener.afterPropertiesSet");
    }
 
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        System.out.println("4.1 => MyApplicationListener.onApplicationEvent");
        if (evt.getApplicationContext().getParent() == null) {
            System.out.println("4.2 => MyApplicationListener.onApplicationEvent");
        }
    }
}

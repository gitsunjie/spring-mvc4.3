package com.sunjie.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationServlet  extends HttpServlet {
	
SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");//设置日期格式,精确到毫秒
    
    public AnnotationServlet(){
        System.out.println("时间："+df.format(new Date())+"执行构造函数...");
    }
    
    public void destroy() {
        this.log("时间："+df.format(new Date())+"执行destroy()方法...");
        //super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    @PostConstruct
    public void someMethod(){
        //this.log("执行@PostConstruct修饰的someMethod()方法...");//注意：这样会出错
        System.out.println("时间："+df.format(new Date())+"执行@PostConstruct修饰的someMethod()方法...");
    }
    
    @PreDestroy
    public void otherMethod(){
        System.out.println("时间："+df.format(new Date())+"执行@PreDestroy修饰的otherMethod()方法...");
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.log("时间："+df.format(new Date())+"执行doGet()方法...");
    }

    public void init() throws ServletException {
        // Put your code here
        this.log("时间："+df.format(new Date())+"执行init()方法...");
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException{
        this.log("时间："+df.format(new Date())+"执行service()方法...");
        super.service(request, response);
    }
}

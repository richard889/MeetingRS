package com.MeetingSrv.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.MeetingSrv.entity.Login;
import com.MeetingSrv.hello.*;
import com.opensymphony.xwork2.ActionSupport;

public class HelloController extends ActionSupport {
	private String message ;
	private ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Override
	public String execute() throws Exception {
		System.out.println("°õ¦æ Action");
		IHello hello = new TwHello();
		message = hello.helloword("Richard");		
		return "success";
	}
	
	public String helloDo() {		
		IHello hello = factory.getBean("helloBean",IHello.class);
		message = hello.helloword("Johny");		
		return "success";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {	
		
		this.message = message;
	}
}

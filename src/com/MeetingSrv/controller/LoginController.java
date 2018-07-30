package com.MeetingSrv.controller;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.MeetingSrv.dao.IDao;
import com.MeetingSrv.dao.LoginDao;
import com.MeetingSrv.dao.MemberDao;
import com.MeetingSrv.entity.Login;
import com.MeetingSrv.entity.Member;
import com.opensymphony.xwork2.ActionSupport;

public class LoginController extends ActionSupport {
	
	private Login login ;
	private Member loginUser;
	private ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		
	@Override
	public String execute() throws Exception {

		return "success";
	}
	
	public String valid() throws SQLException{		
		IDao<Login> dao = factory.getBean("loginDao", IDao.class);
		String userID = login.getAccount();
		String password = login.getPassword();
		
		loginUser = ((LoginDao) dao).QueryUser(new Object[] {userID, password});
		if (loginUser != null)
			return "success";
		else {
			this.addActionError("使用者帳密不存在!");
			return "error";
		}
	}	

	public String regist() {
		return "success";
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Member getMember() {
		return loginUser;
	}

	public void setMember(Member loginUser) {
		this.loginUser = loginUser;
	}
}

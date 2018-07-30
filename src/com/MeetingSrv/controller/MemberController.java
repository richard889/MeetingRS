package com.MeetingSrv.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.FieldError;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.commons.dbcp2.BasicDataSource;

import com.MeetingSrv.dao.IDao;
import com.MeetingSrv.dao.MemberDao;
import com.MeetingSrv.entity.Login;
import com.MeetingSrv.entity.Member;
import com.opensymphony.xwork2.ActionSupport;

public class MemberController extends ActionSupport {

	private Member member = new Member();
	private ServletContext context = ServletActionContext.getServletContext();
	private HttpServletRequest request = ServletActionContext.getRequest();
	private String message;
	private ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	private List<Member> list;

	@Override
	public String execute() throws Exception {

		return "success";
	}

//	public String add() {
//		String driverClassName = context.getInitParameter("driverClassName");
//		String url = context.getInitParameter("url");
//		String userName = context.getInitParameter("userName");
//		String password = context.getInitParameter("password");
//		try {
//			Class.forName(driverClassName);
//			Connection connection = DriverManager.getConnection(url, userName, password);
//			if (connection.isClosed()) {
//				message = "db is connected";
//			}
//		} catch (ClassNotFoundException ex) {
//			ex.printStackTrace();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//
//		return "addok";
//	}

//	public String add() {
//		BasicDataSource datasource = new BasicDataSource();
//		datasource.setDriverClassName(context.getInitParameter("driverClassName"));
//		datasource.setUrl(context.getInitParameter("url"));
//		datasource.setUsername(context.getInitParameter("userName"));
//		datasource.setPassword(context.getInitParameter("password"));
//		try {
//			Connection connection = datasource.getConnection();
//			if (!connection.isClosed()) {
//				message = "db is connected";
//			}
//			connection.close();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		return "addok";
//	}
//	public String add() {
//		DataSource datasource = factory.getBean("dataSource",DataSource.class);
//		
//		try {
//			Connection connection = datasource.getConnection();
//			if (!connection.isClosed()) {
//				message = "db is connected";
//								
//				String sql = "Insert into member(memberID, name, password, phone, address, email) "+
//							 "values(?,?,?,?,?,?)";
//				PreparedStatement st = connection.prepareStatement(sql);
//				st.setString(1,member.getMemberID());
//				st.setString(2,member.getName());
//				st.setString(3,member.getPassword());				
//				st.setString(4,member.getPhone());
//				st.setString(5,member.getAddress());
//				st.setString(6,member.getEmail());
//				
//				int rscnt = st.executeUpdate();
//							
//			}
//			connection.close();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		return "addok";		
//	}
//	public String InsertDAO() throws SQLException {
//		DataSource dataSource = factory.getBean("dataSource", DataSource.class);
//		IDao dao = new MemberDao();
//		dao.setDataSource(dataSource);
//		try {
//			boolean result = dao.insert(member);
//			if (result)
//			{
//				message = "新增成功";
//				return "addok";
//			}
//		} catch (SQLException ex) {
//			message = ex.getMessage();
//		}
//
//		return "addok";
//	}
	

	public String valid() {
		
		if (member.getName() == null || "".equals(member.getName()))
			this.addFieldError("member.name", "使用者名稱不能為空");
		
		if (member.getPassword() == null || "".equals(member.getPassword()))
			this.addFieldError("member.password", "密碼不能為空");
		else {			
			
			Pattern p = Pattern.compile("\\w{6,20}");
			Matcher m = p.matcher(member.getPassword() );
			if(!m.matches())
				this.addFieldError("member.password", "密碼由字母、數字組成，長度為6 ~ 20");
		}
		
		if (hasFieldErrors())
			return INPUT;
		else
			return "validok";
	}
	
	public String AddDAO() throws SQLException {
		IDao<Member> dao = factory.getBean("dao", IDao.class);
		try {
			boolean result = dao.insert(member);
			if (result) {
				member = null;
				message = "新增成功";				
				return "addok";
			}
		} catch (SQLException ex) {
			message = ex.getMessage();
		}

		return "addok";
	}


	public String QryList() throws SQLException {
		IDao<Member> dao = factory.getBean("dao", IDao.class);
		Object[] obj = new Object[] { "", "" };
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		
		if ((account != null) && (!"".equals(account)))
			obj[0] = account;
		if ((name != null) && (!"".equals(name)))
			obj[1] = name;
		
		List<Map<String, Object>> result = ((MemberDao) dao).QueryList(obj);
		list = new ArrayList<Member>();

		for (Map<String, Object> record : result) {
			Member memberObj = new Member();
			memberObj.setMemberID(record.get("memberID").toString());
			memberObj.setName(record.get("name").toString());
			memberObj.setAccount(record.get("account").toString());
			memberObj.setPassword(record.get("password").toString());
			memberObj.setPhone(record.get("phone").toString());
			memberObj.setAddress(record.get("address").toString());
			memberObj.setEmail(record.get("email").toString());
			list.add(memberObj);
		}
		return "qryok";
	}

	public String QryById() throws SQLException {
		IDao<Member> dao = factory.getBean("dao", IDao.class);
		String id = member.getMemberID();
		member = dao.QuerySingle(new Object[] { id });
		return "qryok";
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Member> getList() {
		return list;
	}

	public void setList(List<Member> list) {
		this.list = list;
	}

}

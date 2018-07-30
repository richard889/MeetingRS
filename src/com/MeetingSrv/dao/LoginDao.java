package com.MeetingSrv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.MeetingSrv.entity.Login;
import com.MeetingSrv.entity.Member;

public class LoginDao  implements IDao<Login>{
	private DataSource dataSource;

	public Member QueryUser(Object[] obj) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = dataSource.getConnection();

		String sql = "Select * From member Where account = ? and password = ?";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setString(1, obj[0].toString());
		st.setString(2, obj[1].toString());
		ResultSet rs = st.executeQuery();
		
		Member member = null;
		while (rs.next()) {
			member = new Member();
			member.setMemberID(rs.getString("memberID"));
			member.setName(rs.getString("name"));
			member.setAccount(rs.getString("account"));
			member.setPassword(rs.getString("password"));
			member.setPhone(rs.getString("phone"));
			member.setAddress(rs.getString("address"));
			member.setEmail(rs.getString("email"));
		}
		
		connection.close();
		
		return member;
	}

	@Override
	public boolean insert(Login data) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List QueryMultiple(String sql, Object[] obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login QuerySingle(Object[] obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		this.dataSource = dataSource;
	}

}

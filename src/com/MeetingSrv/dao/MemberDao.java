package com.MeetingSrv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;
import javax.sql.RowSet;

import com.MeetingSrv.entity.Member;
import com.mysql.cj.protocol.Resultset;

public class MemberDao implements IDao<Member> {
	private DataSource dataSource;

	@Override
	public boolean insert(Member member) throws SQLException {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection connection = dataSource.getConnection();

		String sql = "Insert into member(memberID, name, account, password, phone, address, email) " + "values(?,?,?,?,?,?,?)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setString(1, member.getMemberID());
		st.setString(2, member.getName());
		st.setString(3, member.getAccount());
		st.setString(4, member.getPassword());
		st.setString(5, member.getPhone());
		st.setString(6, member.getAddress());
		st.setString(7, member.getEmail());

		int r = st.executeUpdate();
		if (r > 0)
			result = true;

		connection.close();
		return result;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		this.dataSource = dataSource;
	}
	
	@Override
	public List QueryMultiple(String sql, Object[] obj) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = dataSource.getConnection();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();	
		
		PreparedStatement st = connection.prepareStatement(sql);
		int k = 1;
		for (int i = 0; i <= obj.length -1; i++) {
			if ((obj[i]!=null) && (!"".equals(obj[i]))) {
				st.setString(k, obj[i].toString());
				k++;
			}
		}

		ResultSet rs = st.executeQuery();
		ResultSetMetaData md = rs.getMetaData();

		int columnCount = md.getColumnCount();
		while (rs.next()) {
			Map<String, Object> rowData = new HashMap<String, Object>();
//			for (int i = 1; i <= columnCount; i++) {
//				rowData.put(md.getColumnName(i), rs.getObject(i));
//			}
			rowData.put("memberID", rs.getObject(1));
			rowData.put("name", rs.getObject(2));
			rowData.put("account", rs.getObject(3));
			rowData.put("password", rs.getObject(4));
			rowData.put("phone", rs.getObject(5));
			rowData.put("address", rs.getObject(6));
			rowData.put("email", rs.getObject(7));
			list.add(rowData);
		}
		
		connection.close();
		return list;
	}

	@Override
	public Member QuerySingle(Object[] obj) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = dataSource.getConnection();

		String sql = "Select * From member Where memberID = ?";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setString(1, obj[0].toString());
		ResultSet rs = st.executeQuery();
		
		Member member = new Member();
		while (rs.next()) {
			member.setMemberID(rs.getString("memberID"));
			member.setName(rs.getString("name"));
			member.setPassword(rs.getString("password"));
			member.setPhone(rs.getString("phone"));
			member.setAddress(rs.getString("address"));
			member.setEmail(rs.getString("email"));
		}
		
		connection.close();
		return member;
	}

	public List QueryList(Object[] obj) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "Select * From member Where 1=1 ";
		if (!"".equals(obj[0]))
			sql += " And account = ? ";
		if (!"".equals(obj[1]))
			sql += " And name = ? ";	

		List<Map<String, Object>> result = QueryMultiple(sql, obj);
		return result;
	}
}

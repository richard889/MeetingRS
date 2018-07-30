package com.MeetingSrv.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public interface IDao<T> {
	public boolean insert(T data) throws SQLException ;
	public List QueryMultiple(String sql, Object[] obj) throws SQLException;
	public T QuerySingle(Object[] obj) throws SQLException;	
	public void setDataSource(DataSource dataSource);
}

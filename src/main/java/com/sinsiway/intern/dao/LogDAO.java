package com.sinsiway.intern.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.ExecuteLog;

@Component
public class LogDAO {
	@Autowired
	JdbcTemplate jt;

	Connection con = null;

	// insert
	public void connectLog(ConnectionLog clog) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;

		try {
			con = jt.getDataSource().getConnection();
			String sql = "insert into SW_CONNECTION_LOG (database_id, client_ip, connect_date, result) values (?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, clog.getDatabase_id());
			pstmt.setString(2, clog.getClient_ip());
			pstmt.setTimestamp(3, clog.getConnect_date());
			pstmt.setBoolean(4, clog.getResult());
			
			// insert, delete, update 은 executeUpdate()
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void executeLog(ExecuteLog elog) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;

		try {
			con = jt.getDataSource().getConnection();
			String sql = "insert into SW_EXECUTE_LOG (database_id, client_ip, exec_date, sql_text, sql_type, result, message) values (?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, elog.getDatabase_id());
			pstmt.setString(2, elog.getClient_ip());
			pstmt.setTimestamp(3, elog.getExec_date());
			pstmt.setString(4, elog.getSql_text());
			pstmt.setString(5, elog.getSql_type());
			pstmt.setBoolean(6, elog.getResult());
			pstmt.setString(7, elog.getMessage());
			
			// insert, delete, update 은 executeUpdate()
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

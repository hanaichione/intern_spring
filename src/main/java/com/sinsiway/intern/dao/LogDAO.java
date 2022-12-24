package com.sinsiway.intern.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.Db;
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

	public List<ConnectionLog> cfindAll() {
		// TODO Auto-generated method stub
		List<ConnectionLog> list = new ArrayList<ConnectionLog>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
			String sql = "select * from sw_connection_log";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 데이터 받아서 넣기
			while (rs.next()) {
				long id = rs.getLong("id");
				long database_id = rs.getLong("database_id");			
				String client_ip = rs.getString("client_ip");
				Timestamp connect_date = rs.getTimestamp("connect_date");
				boolean result = rs.getBoolean("result");
				ConnectionLog clog = new ConnectionLog(id, database_id, client_ip, connect_date, result);
				list.add(clog);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return list;
	}

	public List<ExecuteLog> efindAll() {
		// TODO Auto-generated method stub
				List<ExecuteLog> list = new ArrayList<ExecuteLog>();
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
					con = jt.getDataSource().getConnection();
					String sql = "select * from sw_execute_log";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();

					// 데이터 받아서 넣기
					while (rs.next()) {
						long id = rs.getLong("id");
						long database_id = rs.getLong("database_id");			
						String client_ip = rs.getString("client_ip");
						Timestamp exec_date = rs.getTimestamp("exec_date");
						String sql_text = rs.getString("sql_text");
						String sql_type = rs.getString("sql_type");
						boolean result = rs.getBoolean("result");
						String message = rs.getString("message");
						ExecuteLog elog = new ExecuteLog(id, database_id, client_ip, exec_date, sql_text, sql_type, result, message);
						list.add(elog);
					}
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					try {
						if (con != null)
							con.close();
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
					} catch (SQLException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

				return list;
	}
	
	

}

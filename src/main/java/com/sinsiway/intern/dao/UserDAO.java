package com.sinsiway.intern.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sinsiway.intern.DTO.User;

@Component
public class UserDAO {
	@Autowired
	JdbcTemplate jt;
	
	// 모두 조회하기
	public List<User> findAll() {
		// 받아온 레코드들을 담을 리스트
		List<User> list = new ArrayList<User>();

		// 필요한 자원
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
			
			String sql = "select * from sw_database";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 데이터 받아서 넣기
			while (rs.next()) {
				long database_id = rs.getLong("database_id");
				int type = rs.getInt("type");
				String ip = rs.getString("ip");
				int port = rs.getInt("port");
				String database = rs.getString("database");
				String username = rs.getString("username");
				String password = rs.getString("password");
				User user = new User(database_id, type, ip, port, database, username, password);
				list.add(user);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return list;
	}

	// 데이터 입력하기
	public void insert(long database_id, int type, String ip, int port, String database, String username,
			String password) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = jt.getDataSource().getConnection();
			// postgresql
//			String sql = "insert into sw_database values (nextval('database_id_seq'), ?, ?, ?, ?, ?, ?)";
			// mariadb
			String sql = "insert into sw_database values (nextval(database_id_seq), ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
//			pstmt.setLong(1, database_id);
//			pstmt.setInt(2, type);
//			pstmt.setString(3, ip);
//			pstmt.setInt(4, port);
//			pstmt.setString(5, database);
//			pstmt.setString(6, username);
//			pstmt.setString(7, password);
			pstmt.setInt(1, type);
			pstmt.setString(2, ip);
			pstmt.setInt(3, port);
			pstmt.setString(4, database);
			pstmt.setString(5, username);
			pstmt.setString(6, password);

			// insert, delete, update 은 executeUpdate()
			int n = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// end insert

	// 데이터 수정하기
	public void update(long database_id, int type, String ip, int port, String database, String username,
			String password) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = jt.getDataSource().getConnection();
			String sql = "update sw_database set type = ?, ip = ?, port = ?, username = ?, password = ? where database_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(6, database_id);
			pstmt.setInt(1, type);
			pstmt.setString(2, ip);
			pstmt.setInt(3, port);
			pstmt.setString(4, username);
			pstmt.setString(5, password);

			int n = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} // update 끝

	// 삭제하기
	public void delete(long database_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = jt.getDataSource().getConnection();

			String sql = "delete from sw_database where database_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, database_id);
			int n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 하나만 조회하기
	public User findOne(long database_id) {
		User user = new User();
		// 필요한 자원
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
			// 접속 시간
			String sql = "select * from sw_database where database_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, database_id);
			rs = pstmt.executeQuery();
			
			rs.next();
			int type = rs.getInt("type");
			String ip = rs.getString("ip");
			int port = rs.getInt("port");
			String database = rs.getString("database");
			String username = rs.getString("username");
			String password = rs.getString("password");
			user = new User(database_id, type, ip, port, database, username, password);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return user;
	}
}

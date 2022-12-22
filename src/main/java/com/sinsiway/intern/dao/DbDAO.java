package com.sinsiway.intern.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sinsiway.intern.dto.Db;

@Component
public class DbDAO {
	@Autowired
	JdbcTemplate jt;
	
	Connection con = null;
	
	// 모두 조회하기
	public List<Db> findAll() {
		// 받아온 레코드들을 담을 리스트
		List<Db> list = new ArrayList<Db>();
		
		// 필요한 자원
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
				Db db = new Db(database_id, type, ip, port, database, username, password);
				list.add(db);
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

		PreparedStatement pstmt = null;

		try {
			con = jt.getDataSource().getConnection();
			String sql = "insert into sw_database values (?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, database_id);
			pstmt.setInt(6, type);
			pstmt.setString(3, ip);
			pstmt.setInt(5, port);
			pstmt.setString(2, database);
			pstmt.setString(7, username);
			pstmt.setString(4, password);

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
	}// end insert

	// 데이터 수정하기
	public void update(long database_id, int type, String ip, int port, String database, String username,
			String password) {
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	} // update 끝

	// 삭제하기
	public void delete(long database_id) {
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
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	// 하나만 조회하기
	public Db findOne(long database_id) {
		Db user = new Db();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
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
			user = new Db(database_id, type, ip, port, database, username, password);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return user;
	}
}

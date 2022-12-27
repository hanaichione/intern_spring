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
public class DbDao {
	@Autowired
	JdbcTemplate jt;
	
	Connection con = null;
	
	// 모두 조회하기
	public Object findAll() {
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
			return "조회 실패" + e.getMessage();
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

	// 데이터 입력하기
	public String insert(long database_id, int type, String ip, int port, String database, String username,
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
			return "입력 실패"+e.getMessage();
		} finally {
			try {
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "입력 성공";
	}// end insert

	// 데이터 수정하기
	public String update(long database_id, int type, String ip, int port, String database, String username,
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

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return "수정 실패"+e.getMessage();
		} finally {
			try {
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "수정 성공";
	} // update 끝

	// 삭제하기
	public String delete(long database_id) {
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
			return "삭제 실패" + e.getMessage();
		} finally {
			if (pstmt != null)
				try {
					if (con != null)
						con.close();
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return "삭제 성공";
	}

	// 하나만 조회하기
	public Object findOne(long database_id) {
		Db db = new Db();
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
			db = new Db(database_id, type, ip, port, database, username, password);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "조회 실패"+e.getMessage();
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

		return db;
	}

	public Object findDup(int type, String ip, int port, String database, String username, String password) {
		// TODO Auto-generated method stub
		Db db = new Db();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
			String sql = "select * from sw_database where type = ? and ip = ? and port = ? and database = ? and username = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, type);
			pstmt.setString(2, ip);
			pstmt.setInt(3, port);
			pstmt.setString(4, database);
			pstmt.setString(5, username);
			pstmt.setString(6, password);
			
			
			rs = pstmt.executeQuery();
			
			rs.next();
			long database_id = rs.getLong("database_id");
			db = new Db(database_id, type, ip, port, database, username, password);

		} catch (Exception e) {
			// TODO: handle exception
			return null;
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

		return db;
	}
}

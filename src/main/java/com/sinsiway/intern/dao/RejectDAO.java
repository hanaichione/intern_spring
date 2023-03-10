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

import com.sinsiway.intern.dto.Emp;
import com.sinsiway.intern.dto.Reject;

@Component
public class RejectDao {
	@Autowired
	JdbcTemplate jt;

	Connection con = null;

	public int insertReject(Reject reject) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = jt.getDataSource().getConnection();
			String sql = "insert into sw_database_reject (database_id, client_ip) values (?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reject.getDatabase_id());
			pstmt.setString(2, reject.getClient_ip());

			// insert, delete, update 은 executeUpdate()
			n = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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
		return n;
	}

	public int updateReject(Reject reject) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = jt.getDataSource().getConnection();
			String sql = "update sw_database_reject set client_ip = ?, database_id =? where policy_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(3, reject.getPolicy_id());
			pstmt.setLong(2, reject.getDatabase_id());
			pstmt.setString(1, reject.getClient_ip());
			n = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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
		return n;
	}

	public Object rejectFindAll() {
		// 받아온 레코드들을 담을 리스트
		List<Reject> list = new ArrayList<Reject>();

		// 필요한 자원
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
			String sql = "select * from sw_database_reject";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 데이터 받아서 넣기
			while (rs.next()) {
				long database_id = rs.getLong("database_id");
				long policy_id = rs.getLong("policy_id");
				String client_ip = rs.getString("client_ip");
				Reject reject = new Reject(policy_id, database_id, client_ip);
				list.add(reject);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "sw_database_reject 테이블 조회 실패";
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

	public Object rejectFindOne(Long policy_id) {
		// TODO Auto-generated method stub
		Reject reject = new Reject();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
			// 접속 시간
			String sql = "select * from sw_database_reject where policy_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, policy_id);
			rs = pstmt.executeQuery();

			rs.next();
			long database_id = rs.getLong("database_id");
			String client_ip = rs.getString("client_ip");
			reject = new Reject(policy_id, database_id, client_ip);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "sw_database_reject 테이블 조회 실패";
		} finally {
			try {
				if(con != null)
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
		return reject;
	}

	public int deleteReject(Long policy_id) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = jt.getDataSource().getConnection();
			String sql = "delete from sw_database_reject where policy_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, policy_id);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n;
	}

	public Object rejectFindOne(long database_id) {
		// TODO Auto-generated method stub
		List<Reject> reject = new ArrayList<Reject>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
			// 접속 시간
			String sql = "select * from sw_database_reject where database_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, database_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				long policy_id = rs.getLong("policy_id");
				String client_ip = rs.getString("client_ip");
				Reject r = new Reject(policy_id, database_id, client_ip);
				reject.add(r);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "sw_database_reject 테이블 조회 실패";
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
		return reject;
	}

	public Object rejectFindOne(String ip) {
		// TODO Auto-generated method stub
		Reject reject = new Reject();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
			// 접속 시간
			String sql = "select * from sw_database_reject where client_ip = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ip);
			rs = pstmt.executeQuery();
			
			rs.next();
			long policy_id = rs.getLong("policy_id");
			long database_id = rs.getLong("database_id");
			reject = new Reject(policy_id, database_id, ip);

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
		return reject;
	}

	public Object findDupIp(long database_id, String client_ip) {
		// TODO Auto-generated method stub
		Reject reject = new Reject();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = jt.getDataSource().getConnection();
			// 접속 시간
			String sql = "select * from sw_database_reject where client_ip = ? and database_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, client_ip);
			pstmt.setLong(2, database_id);
			rs = pstmt.executeQuery();
			
			rs.next();
			long policy_id = rs.getLong("policy_id");
			reject = new Reject(policy_id, database_id, client_ip);

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
		return reject;
	}
}

package com.sinsiway.intern.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sinsiway.intern.DTO.Reject;
import com.sinsiway.intern.DTO.User;

@Component
public class RejectDAO {

	public int insertReject(Connection con, Reject reject) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			// postgresql
			String sql = "insert into sw_database values (?, ?, ?)";
			// mariadb
//			String sql = "insert into sw_database values (nextval(database_id_seq), ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, reject.getPolicy_id());
			pstmt.setLong(2, reject.getDatabase_id());
			pstmt.setString(3, reject.getClient_ip());

			// insert, delete, update 은 executeUpdate()
			n = pstmt.executeUpdate();

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
		return n;
	}

	public int updateReject(Connection con, Reject reject) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			String sql = "update sw_database_reject client_ip where database_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(2, reject.getDatabase_id());
			pstmt.setString(1, reject.getClient_ip());
			n = pstmt.executeUpdate();

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
		return n;
	}

	public List<Reject> rejectFindAll(Connection con) {
		// 받아온 레코드들을 담을 리스트
				List<Reject> list = new ArrayList<Reject>();

				// 필요한 자원
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {
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

	public Reject rejectFindOne(Connection con, Long database_id) {
		// TODO Auto-generated method stub
		Reject reject = new Reject();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 접속 시간
			String sql = "select * from sw_database_reject where database_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, database_id);
			rs = pstmt.executeQuery();
			
			rs.next();
			long policy_id = rs.getLong("policy_id");
			String client_ip = rs.getString("client_ip");
			reject = new Reject(policy_id, database_id, client_ip);

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
		return reject;
	}

	public int deleteReject(Connection con, Long database_id) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			String sql = "delete from sw_database_reject where database_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, database_id);
			n = pstmt.executeUpdate();
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
		return n;
	}

}

package com.sinsiway.intern.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sinsiway.intern.dto.Dept;
import com.sinsiway.intern.dto.Emp;

@Component
public class QueryDao {

	public Object select(Connection con, String sql, String type) {
//		public List<Emp> selectE(Connection con, String sql, String type) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Object resp = null;

		try {
			pstmt = con.prepareStatement(sql);
			
			// select일 때
			if (type.equalsIgnoreCase("select")) { // 수정, 부족한 부분 정리할 떄 참고(원인 분석, 개선 방안, 비전/어떤 계획을 갖고 있는지)
				rs = pstmt.executeQuery();
				String table = "";
				String[] sql_word = sql.split(" ");
				for (int i = 0; i < sql_word.length-1; i++) {
					if(sql_word[i].equalsIgnoreCase("from")) {
						table = sql_word[i+1];
					}
				}
				
				// emp 일 때
				if (table.equalsIgnoreCase("emp")) {
					System.out.println("query type - select, table - emp");
					resp = new ArrayList<Emp>();
					// 데이터 받아서 넣기
					// 이 부분의 확장성 -> 모든 테이블 레코드를 받아올 수 있도록
					while (rs.next()) { // java connection, result 중 column을 동적으로 받아오는 방법
						
						int empno = rs.getInt("empno");
						String ename = rs.getString("ename");
						String job = rs.getString("job");
						int mgr = rs.getInt("mgr");
						Date hiredate = rs.getDate("hiredate");
						double sal = rs.getDouble("sal");
						double comm = rs.getDouble("comm");
						int deptno = rs.getInt("deptno");
						Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
						((ArrayList<Emp>) resp).add(emp);
					}
				} else if (table.equalsIgnoreCase("dept")) {
					resp = new ArrayList<Dept>();
					
					while (rs.next()) {
						int deptno = rs.getInt("deptno");
						String dname = rs.getString("dname");
						String loc = rs.getString("loc");
						Dept dept = new Dept(deptno, dname, loc);
						((ArrayList<Dept>) resp).add(dept);
					}
				}
			}
			// insert, update, delete일 때
			else {
				int n = pstmt.executeUpdate();
				if (n == 1 && type.equalsIgnoreCase("insert")) {
					resp = "등록 성공";
					System.out.println("insert 성공");
				} else if (n == 1 && type.equalsIgnoreCase("update")) {
					System.out.println("update 성공");
					resp = "수정 성공";
				} else if (n == 1 && type.equalsIgnoreCase("delete")) {
					System.out.println("delete 성공");
					resp = "삭제 성공";
				} else if (n == 0) {
					resp = "구문 수행 실패";
				}
			}

		} catch (Exception e) {
			resp = e.getMessage();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resp;
	}
	
//	public List<Dept> selectD(Connection con, String sql, String type) {
//		// TODO Auto-generated method stub
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<Dept> list = new ArrayList<Dept>();
//
//		try {
//			pstmt = con.prepareStatement(sql);
//			// select일 때
//			if (type.equals("select")) {
//				rs = pstmt.executeQuery();
//
//				// 데이터 받아서 넣기
//				while (rs.next()) {
//					int deptno = rs.getInt("deptno");
//					String dname = rs.getString("dname");
//					String loc = rs.getString("loc");
//					Dept dept = new Dept(deptno, dname, loc);
//					list.add(dept);
//				}
//			}
//			// insert, update, put일 때
//			else {
//				pstmt.executeUpdate();
//				// 수행 메시지 위해서 전부 구별할 필요 有
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//				if (rs != null)
//					rs.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return list;
//	}

}

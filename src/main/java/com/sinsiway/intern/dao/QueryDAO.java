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
public class QueryDAO {

	public List<Emp> selectE(Connection con, String sql, String type) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Emp> list = new ArrayList<Emp>();

		try {
			pstmt = con.prepareStatement(sql);
			// select일 때
			if (type.equals("select")) {
				rs = pstmt.executeQuery();

				// 데이터 받아서 넣기
				while (rs.next()) {
					int empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					String job = rs.getString("job");
					int mgr = rs.getInt("mgr");
					Date hiredate = rs.getDate("hiredate");
					double sal = rs.getDouble("sal");
					double comm = rs.getDouble("comm");
					int deptno = rs.getInt("deptno");
					Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
					list.add(emp);
				}
			}
			// insert, update, put일 때
			else {
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
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
		return list;
	}
	
	public List<Dept> selectD(Connection con, String sql, String type) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Dept> list = new ArrayList<Dept>();

		try {
			pstmt = con.prepareStatement(sql);
			// select일 때
			if (type.equals("select")) {
				rs = pstmt.executeQuery();

				// 데이터 받아서 넣기
				while (rs.next()) {
					int deptno = rs.getInt("deptno");
					String dname = rs.getString("dname");
					String loc = rs.getString("loc");
					Dept dept = new Dept(deptno, dname, loc);
					list.add(dept);
				}
			}
			// insert, update, put일 때
			else {
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
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
		return list;
	}

}

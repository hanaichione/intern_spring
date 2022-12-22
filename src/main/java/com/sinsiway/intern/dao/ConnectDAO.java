package com.sinsiway.intern.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.Db;

@Component
public class ConnectDAO {
	
	public Connection connect(Db db) {
		Connection con = null;
		
		String driver = "org.mariadb.jdbc.Driver";
		String url = db.getIp();
		String userid = db.getUsername();
		String passwd = db.getPassword();
		
		// 드라이버 로딩
		try {
			Class.forName(driver);
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 접속
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			System.out.println("연결 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public String close(Connection con) {
		if (con!=null)
			try {
				con.close();
				return "접속 종료";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "접속 종료 실패";
	}

}

package com.sinsiway.intern.service;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinsiway.intern.dao.ConnectDAO;
import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.Db;

@Service
public class ConnectService {
	
	@Autowired
	ConnectDAO dao;
	
	public Connection connect(Db db) {
		// TODO Auto-generated method stub
		System.out.println("service에서 호출 성공");
		return dao.connect(db);
	}

	public void close(Connection con) {
		// TODO Auto-generated method stub
		dao.close(con);
	}

}

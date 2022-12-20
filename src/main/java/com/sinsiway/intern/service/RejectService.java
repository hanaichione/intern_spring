package com.sinsiway.intern.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinsiway.intern.DTO.Reject;
import com.sinsiway.intern.dao.RejectDAO;

@Service
public class RejectService {
	
	@Autowired
	RejectDAO dao;
	
	public int insertReject(Connection con, Reject reject) {
		// TODO Auto-generated method stub
		return dao.insertReject(con, reject);
	}

	public int updateReject(Connection con, Reject reject) {
		// TODO Auto-generated method stub
		return dao.updateReject(con, reject);
	}

	public List<Reject> rejectFindAll(Connection con) {
		// TODO Auto-generated method stub
		return dao.rejectFindAll(con);
	}

	public Reject rejectFindOne(Connection con, Long database_id) {
		// TODO Auto-generated method stub
		return dao.rejectFindOne(con, database_id);
	}

	public int deleteReject(Connection con, Long database_id) {
		// TODO Auto-generated method stub
		return dao.deleteReject(con, database_id);
	}
	
	

}

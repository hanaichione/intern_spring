package com.sinsiway.intern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinsiway.intern.dao.RejectDao;
import com.sinsiway.intern.dto.Reject;

@Service
public class RejectService {
	
	@Autowired
	RejectDao dao;
	
	public int insertReject(Reject reject) {
		// TODO Auto-generated method stub
		return dao.insertReject(reject);
	}

	public int updateReject(Reject reject) {
		// TODO Auto-generated method stub
		return dao.updateReject(reject);
	}

	public Object rejectFindAll() {
		// TODO Auto-generated method stub
		return dao.rejectFindAll();
	}
	
	public Object rejectFindOne(Long policy_id) {
		// TODO Auto-generated method stub
		return dao.rejectFindOne(policy_id);
	}

	public Object rejectFindOne(long database_id) {
		// TODO Auto-generated method stub
		return dao.rejectFindOne(database_id);
	}
	
	public Object rejectFindOne(String ip) {
		// TODO Auto-generated method stub
		return dao.rejectFindOne(ip);
	}

	public int deleteReject(Long policy_id) {
		// TODO Auto-generated method stub
		return dao.deleteReject(policy_id);
	}

}

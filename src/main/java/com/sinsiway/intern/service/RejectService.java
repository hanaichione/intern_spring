package com.sinsiway.intern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinsiway.intern.dao.RejectDAO;
import com.sinsiway.intern.dto.Reject;

@Service
public class RejectService {
	
	@Autowired
	RejectDAO dao;
	
	public int insertReject(Reject reject) {
		// TODO Auto-generated method stub
		return dao.insertReject(reject);
	}

	public int updateReject(Reject reject) {
		// TODO Auto-generated method stub
		return dao.updateReject(reject);
	}

	public List<Reject> rejectFindAll() {
		// TODO Auto-generated method stub
		return dao.rejectFindAll();
	}

	public Reject rejectFindOne(Long policy_id) {
		// TODO Auto-generated method stub
		return dao.rejectFindOne(policy_id);
	}

	public int deleteReject(Long policy_id) {
		// TODO Auto-generated method stub
		return dao.deleteReject(policy_id);
	}

	public Object rejectFindOne(String ip) {
		// TODO Auto-generated method stub
		return dao.rejectFindOne(ip);
	}
	
	

}

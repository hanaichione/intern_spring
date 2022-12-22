package com.sinsiway.intern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinsiway.intern.dao.LogDAO;
import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.ExecuteLog;

@Service
public class LogService {
	
	@Autowired
	LogDAO dao;
	
	public void connectLog(ConnectionLog clog) {
		// TODO Auto-generated method stub
		dao.connectLog(clog);
	}

	public void executeLog(ExecuteLog elog) {
		// TODO Auto-generated method stub
		dao.executeLog(elog);
	}
}

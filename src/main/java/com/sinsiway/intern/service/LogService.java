package com.sinsiway.intern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinsiway.intern.dao.LogDao;
import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.ExecuteLog;

@Service
public class LogService {
	
	@Autowired
	LogDao dao;
	
	public String connectLog(ConnectionLog clog) {
		// TODO Auto-generated method stub
		return dao.connectLog(clog);
	}

	public String executeLog(ExecuteLog elog) {
		// TODO Auto-generated method stub
		return dao.executeLog(elog);
	}

	public Object cfindAll() {
		// TODO Auto-generated method stub
		return dao.cfindAll();
	}

	public Object efindAll() {
		// TODO Auto-generated method stub
		return dao.efindAll();
	}

	public Object cfindById(long database_id) {
		// TODO Auto-generated method stub
		return dao.cfindById(database_id);
	}

	public Object efindById(long database_id) {
		// TODO Auto-generated method stub
		return dao.efindById(database_id);
	}
}

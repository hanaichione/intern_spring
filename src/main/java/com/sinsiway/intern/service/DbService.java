package com.sinsiway.intern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinsiway.intern.dao.DbDao;
import com.sinsiway.intern.dto.Db;

@Transactional
@Service
public class DbService {
	@Autowired
	private DbDao dbDao;
	
	public Object findAll(){
		return dbDao.findAll();
	}
	
	public Object findOne(long database_id) {
		return dbDao.findOne(database_id);
	}
	
	public String insert(long database_id, int type, String ip, int port, String database, String username,
			String password) {
		return dbDao.insert(database_id, type, ip, port, database, username, password);
	}
	
	public String update(long database_id, int type, String ip, int port, String database, String username,
			String password) {
		return dbDao.update(database_id, type, ip, port, database, username, password);
	}
	
	public String delete(long database_id) {
		return dbDao.delete(database_id);
	}

	
}

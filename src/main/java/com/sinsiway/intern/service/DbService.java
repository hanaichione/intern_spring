package com.sinsiway.intern.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinsiway.intern.dao.DbDAO;
import com.sinsiway.intern.dto.Db;

@Transactional
@Service
public class DbService {
	@Autowired
	private DbDAO dbDao;
	
	public List<Db> findAll(){
		return dbDao.findAll();
	}
	
	public Db findOne(long database_id) {
		return dbDao.findOne(database_id);
	}
	
	public void insert(long database_id, int type, String ip, int port, String database, String username,
			String password) {
		dbDao.insert(database_id, type, ip, port, database, username, password);
	}
	
	public void update(long database_id, int type, String ip, int port, String database, String username,
			String password) {
		dbDao.update(database_id, type, ip, port, database, username, password);
	}
	
	public void delete(long database_id) {
		dbDao.delete(database_id);
	}

	
}

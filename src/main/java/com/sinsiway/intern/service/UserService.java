package com.sinsiway.intern.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinsiway.intern.DTO.User;
import com.sinsiway.intern.dao.UserDAO;

@Transactional
@Service
public class UserService {
	@Autowired
	private UserDAO userDao;
	
	public List<User> findAll(Connection con){
		return userDao.findAll(con);
	}
	
	public User findOne(Connection con, long database_id) {
		return userDao.findOne(con, database_id);
	}
	
	public void insert(Connection con, long database_id, int type, String ip, int port, String database, String username,
			String password) {
		userDao.insert(con, database_id, type, ip, port, database, username, password);
	}
	
	public void update(Connection con, long database_id, int type, String ip, int port, String database, String username,
			String password) {
		userDao.update(con, database_id, type, ip, port, database, username, password);
	}
	
	public void delete(Connection con, long database_id) {
		userDao.delete(con, database_id);
	}

	public Connection connect() {
		// TODO Auto-generated method stub
		System.out.println("service에서 호출 성공");
		return userDao.connect();
	}

	public void close(Connection con) {
		// TODO Auto-generated method stub
		userDao.close(con);
	}
}

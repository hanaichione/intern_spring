package com.sinsiway.intern.service;

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
	
	public List<User> findAll(){
		return userDao.findAll();
	}
	
	public User findOne(long database_id) {
		return userDao.findOne(database_id);
	}
	
	public void insert(long database_id, int type, String ip, int port, String database, String username,
			String password) {
		userDao.insert(database_id, type, ip, port, database, username, password);
	}
	
	public void update(long database_id, int type, String ip, int port, String database, String username,
			String password) {
		userDao.update(database_id, type, ip, port, database, username, password);
	}
	
	public void delete(long database_id) {
		userDao.delete(database_id);
	}
}

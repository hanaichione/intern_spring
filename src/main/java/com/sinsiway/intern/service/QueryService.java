package com.sinsiway.intern.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinsiway.intern.dao.QueryDao;
import com.sinsiway.intern.dto.Dept;
import com.sinsiway.intern.dto.Emp;

@Service
public class QueryService {

	@Autowired
	QueryDao dao;
	
	public Object select(Connection con, String sql, String type) {
		// TODO Auto-generated method stub
		return dao.select(con, sql, type);
	}

//	public List<Dept> selectD(Connection con, String sql, String type) {
//		// TODO Auto-generated method stub
//		return dao.selectD(con, sql, type);
//	}
}

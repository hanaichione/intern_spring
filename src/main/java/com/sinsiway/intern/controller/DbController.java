package com.sinsiway.intern.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sinsiway.intern.dto.Db;
import com.sinsiway.intern.service.DbService;

@RestController
public class DbController {
	@Autowired
	private DbService service;
	@Autowired
	HttpSession session;
	
	@PostMapping("/db")
	public String insert(@RequestBody Db db) {
		
			// TODO: handle exception
			// 세션에 아이디가 없다 == insert 한 적이 없다
			service.insert(db.getDatabase_id(), db.getType(), db.getIp(), db.getPort(), db.getDatabase(),
					db.getUsername(), db.getPassword());
			return "입력 성공";
	}

	@GetMapping("/db")
	public List<Db> findAll() {
		List<Db> db = service.findAll();
		return db;
	}

	@GetMapping("/db/{database_id}")
	public Db findOne(@PathVariable("database_id") Long database_id) {
		Db db = service.findOne(database_id);
		return db;
	}

	@DeleteMapping("/db/{database_id}")
	public String delete(@PathVariable("database_id") Long database_id) {
		service.delete(database_id);
		session.setAttribute("check", null);
		return "삭제 완료";
	}

	@PutMapping("/db/{database_id}")
	public Db update(@RequestBody Db db, @PathVariable("database_id") Long database_id) {
		service.update(database_id, db.getType(), db.getIp(), db.getPort(), db.getDatabase(),
				db.getUsername(), db.getPassword());
		return service.findOne(database_id);
	}
}

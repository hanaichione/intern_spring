package com.sinsiway.intern.controller;

import java.util.HashMap;
import java.util.Map;

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
	public Map<String, String> insert(@RequestBody Db db) {
		Map<String, String> response = new HashMap<String, String>();
		String result = service.insert(db.getDatabase_id(), db.getType(), db.getIp(), db.getPort(), db.getDatabase(), db.getUsername(),
				db.getPassword());
		response.put("result", result);
		return response;
	}

	@GetMapping("/db")
	public Object findAll() {
		return service.findAll();
	}

	@GetMapping("/db/{database_id}")
	public Object findOne(@PathVariable("database_id") Long database_id) {
		return service.findOne(database_id);
	}

	@DeleteMapping("/db/{database_id}")
	public Object delete(@PathVariable("database_id") Long database_id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Object result = service.delete(database_id); // 삭제 성공 or 삭제 실패
		response.put("result", result);
		return response;
	}

	@PutMapping("/db/{database_id}")
	public Object update(@RequestBody Db db, @PathVariable("database_id") Long database_id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Object result = service.update(database_id, db.getType(), db.getIp(), db.getPort(), db.getDatabase(), db.getUsername(),
				db.getPassword()); // 수정 성공 or 수정 실패
		response.put("result", result);
		return response;
	}
}

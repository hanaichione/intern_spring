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

	@PostMapping("/dbs")
	public Map<String, String> insert(@RequestBody Db db) {
		Map<String, String> response = new HashMap<String, String>();
		String result = "";
		
		// 입력 값 중 null 값이 있는 경우
		if (db.getIp().length() == 0 || db.getDatabase().length() == 0 || 
				db.getUsername().length() == 0 || db.getPassword().length() == 0) {
			result = "접속 정보는 비워둘 수 없습니다.";
		} else {
			// 동일한 접속 정보를 저장하는 경우
			Object db_info = service.findDup(db.getType(), db.getIp(), db.getPort(), db.getDatabase(), db.getUsername(),
					db.getPassword());
			
			if (db_info != null) {
				result = "해당 접속 정보는 이미 데이터베이스에 있습니다.";
			} else if (db_info == null) {
				result = service.insert(db.getDatabase_id(), db.getType(), db.getIp(), db.getPort(), db.getDatabase(), db.getUsername(),
						db.getPassword());
			}
		}
		response.put("result", result);
		return response;
	}

	@GetMapping("/dbs")
	public Object findAll() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", service.findAll());
		return response;
	}

	@GetMapping("/dbs/{database_id}")
	public Object findOne(@PathVariable("database_id") Long database_id) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", service.findOne(database_id));
		return response;
	}

	@DeleteMapping("/dbs/{database_id}")
	public Object delete(@PathVariable("database_id") Long database_id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Object result = service.delete(database_id); // 삭제 성공 or 삭제 실패
		response.put("result", result);
		return response;
	}

	@PutMapping("/dbs/{database_id}")
	public Object update(@RequestBody Db db, @PathVariable("database_id") Long database_id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Object result = service.update(database_id, db.getType(), db.getIp(), db.getPort(), db.getDatabase(), db.getUsername(),
				db.getPassword()); // 수정 성공 or 수정 실패
		response.put("result", result);
		return response;
	}
}

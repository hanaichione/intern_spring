package com.sinsiway.intern.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sinsiway.intern.DTO.Reject;
import com.sinsiway.intern.service.RejectService;

@RestController
public class RejectController {
	@Autowired
	RejectService service;
	
	// 404 not found 오류 발생하여 수정 필요
	@PostMapping("/rejInsert")
	public int insertReject(@RequestBody Reject reject, HttpSession session) {
		Connection con = (Connection) session.getAttribute("con");
		return service.insertReject(con, reject);
	}
	
	@PutMapping("/rejectUpdate/{database_id}")
	public int updateReject(@RequestBody Reject reject, @PathVariable("database_id") Long database_id, HttpSession session) {
		Connection con = (Connection) session.getAttribute("con");
		reject.setDatabase_id(database_id);
		return service.updateReject(con, reject);
	}
	
	@GetMapping("/rejectFindAll")
	public List<Reject> rejectFindAll(HttpSession session) {
		Connection con = (Connection) session.getAttribute("con");
		List<Reject> reject = service.rejectFindAll(con);
		return reject;
	}
	
	@GetMapping("/rejectFindOne/{database_id}")
	public Reject rejectFindOne(HttpSession session, @PathVariable("database_id") Long database_id) {
		Connection con = (Connection) session.getAttribute("con");
		return service.rejectFindOne(con, database_id);
	}
	
	@DeleteMapping("/deleteReject/{database_id}")
	public int deleteReject(@PathVariable("database_id") Long database_id, HttpSession session) {
		Connection con = (Connection) session.getAttribute("con");
		return service.deleteReject(con, database_id);
	}
}

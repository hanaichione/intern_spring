package com.sinsiway.intern.controller;

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

import com.sinsiway.intern.dto.Reject;
import com.sinsiway.intern.service.RejectService;

@RestController
public class RejectController {
	@Autowired
	RejectService service;
	
	@PostMapping("/reject")
	public String insertReject(@RequestBody Reject reject, HttpSession session) {
		service.insertReject(reject);
		return "입력완료";
	}
	
	@PutMapping("/reject")
	public Reject updateReject(@RequestBody Reject reject) {
		service.updateReject(reject);
		return service.rejectFindOne(reject.getPolicy_id());
	}
	
	@GetMapping("/reject")
	public List<Reject> rejectFindAll() {
		List<Reject> reject = service.rejectFindAll();
		return reject;
	}
	
	@GetMapping("/reject/{policy_id}")
	public Reject rejectFindOne(@PathVariable("policy_id") Long policy_id) {
		return service.rejectFindOne(policy_id);
	}
	
	@DeleteMapping("/reject/{policy_id}")
	public List<Reject> deleteReject(@PathVariable("policy_id") Long policy_id) {
		service.deleteReject(policy_id);
		return service.rejectFindAll();
	}
}

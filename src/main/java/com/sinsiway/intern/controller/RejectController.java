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

import com.sinsiway.intern.dto.Reject;
import com.sinsiway.intern.service.RejectService;

@RestController
public class RejectController {
	@Autowired
	RejectService service;
	
	
	@PostMapping("/rejections")
	public Object insertReject(@RequestBody Reject reject, HttpSession session) {
		Map<String, Object> response = new HashMap<String, Object>();
		String result = "";
		
		if (reject.getClient_ip().length() == 0) { // client ip 정보가 비어있을 경우
			result = "client ip 정보는 비워둘 수 없습니다.";
		} else {									// 이미 저장된 정보를 중복 저장하는 경우
			Object rej = service.findDupIp(reject.getDatabase_id(), reject.getClient_ip());
			if (rej != null) {
				result = "해당 데이터베이스에 이미 접근 제한된 IP 입니다.";
			} else {
				int n =	service.insertReject(reject);
				if (n == 1) {
					result = "입력 완료";
				} else {
					result = "입력 실패";	
				}
			}
		}
		
		response.put("result", result);
		return response;
	}
	
	@PutMapping("/rejections")
	public Object updateReject(@RequestBody Reject reject) {
		Map<String, Object> response = new HashMap<String, Object>();
		String result = "";
		int n =	service.updateReject(reject);
		if (n == 1) {
			result = "수정 완료";
		} else {
			result = "수정 실패";	
		}
		response.put("result", result);
		return response;
	}
	
	@GetMapping("/rejections")
	public Object rejectFindAll() {
		Map<String, Object> response = new HashMap<String, Object>();
		Object result = service.rejectFindAll();
		response.put("result", result);
		return response;
	}
	
	@GetMapping("/rejections/{policy_id}")
	public Object rejectFindOne(@PathVariable("policy_id") Long policy_id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Object result = service.rejectFindOne(policy_id);
		response.put("result", result);
		return response;
	}
	
	@DeleteMapping("/rejections/{policy_id}")
	public Object deleteReject(@PathVariable("policy_id") Long policy_id) {
		Map<String, Object> response = new HashMap<String, Object>();
		String result = "";
		int n =	service.deleteReject(policy_id);
		if (n == 1) {
			result = "삭제 완료";
		} else {
			result = "삭제 실패";	
		}
		response.put("result", result);
		return response;
	}
}

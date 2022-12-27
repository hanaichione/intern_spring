package com.sinsiway.intern.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.ExecuteLog;
import com.sinsiway.intern.service.LogService;

@RestController
public class LogController {
	@Autowired
	LogService logService;
	
	@GetMapping("/clogs")
	public Object cfindAll(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", logService.cfindAll());
		return response;
	}
	
	@GetMapping("/elogs")
	public Object efindAll(){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", logService.efindAll());
		return response;
	}
	
	@GetMapping("/clogs/{database_id}")
	public Object cfindWhere(@PathVariable long database_id){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", logService.cfindById(database_id));
		return response;
	}
	
	@GetMapping("/elogs/{database_id}")
	public Object efindWhere(@PathVariable long database_id){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", logService.efindById(database_id));
		return response;
	}
}

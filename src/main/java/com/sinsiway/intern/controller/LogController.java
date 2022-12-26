package com.sinsiway.intern.controller;

import java.util.List;

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
	
	@GetMapping("/clog")
	public Object cfindAll(){
		return logService.cfindAll();
	}
	
	@GetMapping("/elog")
	public Object efindAll(){
		return logService.efindAll();
	}
	
	@GetMapping("/clog/{database_id}")
	public Object cfindWhere(@PathVariable long database_id){
		Object result = logService.cfindById(database_id);
		return result;
	}
	
	@GetMapping("/elog/{database_id}")
	public Object efindWhere(@PathVariable long database_id){
		Object result = logService.efindById(database_id);
		return result;
	}
}

package com.sinsiway.intern.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.ExecuteLog;
import com.sinsiway.intern.service.LogService;

@RestController
public class LogController {
	@Autowired
	LogService logService;
	
	@GetMapping("/clog")
	public List<ConnectionLog> cfindAll(){
		return logService.cfindAll();
	}
	
	@GetMapping("/elog")
	public List<ExecuteLog> efindAll(){
		return logService.efindAll();
	}
}

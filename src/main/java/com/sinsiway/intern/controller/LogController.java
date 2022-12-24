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
	public List<ConnectionLog> cfindAll(){
		return logService.cfindAll();
	}
	
	@GetMapping("/elog")
	public List<ExecuteLog> efindAll(){
		return logService.efindAll();
	}
	
	@GetMapping("/clog/{database_id}")
	public List<ConnectionLog> cfindWhere(@PathVariable long database_id){
		List<ConnectionLog> list = logService.cfindAll();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDatabase_id() == database_id) {
				list.remove(i);
			}
		}
		return list;
	}
	
	@GetMapping("/elog/{database_id}")
	public List<ExecuteLog> efindWhere(@PathVariable long database_id){
		List<ExecuteLog> list = logService.efindAll();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDatabase_id() == database_id) {
				list.remove(i);
			}
		}
		return list;
	}
}

package com.sinsiway.intern.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.Db;
import com.sinsiway.intern.service.DbService;
import com.sinsiway.intern.service.LogService;
import com.sinsiway.intern.service.ConnectService;

@RestController
public class ConnectController {
	
	@Autowired
	ConnectService service;
	@Autowired
	LogService logService;
	@Autowired
	DbService dbService;
	@Autowired
	HttpSession session;
	
	static int cnt = 0;
	
	// 접속
	@GetMapping("/connect/{database_id}")
	public String connect(@PathVariable long database_id, HttpServletRequest request) {
		// connectlog
		ConnectionLog clog = new ConnectionLog();
		
		// client_ip
		String ip = request.getHeader("X-Forwarded-For");
		//proxy 환경일 경우
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        //웹로직 서버일 경우
        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
		if(ip==null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		
		// connect 시도
		Connection con = null;
		Db db = dbService.findOne(database_id);
		con = service.connect(db);
		
		// 접속 시간
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		
		clog.setClient_ip(ip);
		clog.setConnect_date(now);
		clog.setDatabase_id(database_id);
		clog.setResult(true);
		
		logService.connectLog(clog);
		
		cnt++;
		session.setAttribute("token"+database_id+cnt, cnt); //token21, 1
		System.out.println("token"+database_id+cnt);
		session.setAttribute("token"+database_id+cnt+"con", con); // token21con, con
		System.out.println("token"+database_id+cnt+"con");
		session.setAttribute("token"+database_id+cnt+"dbId", database_id);
		
		return "접속 성공, 토큰 아이디 : "+database_id+session.getAttribute("token"+database_id+cnt);
	}

	// 접속 종료
	@GetMapping("/close/{token}")
	public String close(@PathVariable int token) {
		Connection con = (Connection) session.getAttribute("token"+token+"con"); // token21con
		service.close(con);
		session.invalidate();
		return "접속 종료";
	}
	
}

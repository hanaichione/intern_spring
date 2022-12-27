package com.sinsiway.intern.controller;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sinsiway.intern.dto.ConnectionLog;
import com.sinsiway.intern.dto.Db;
import com.sinsiway.intern.dto.Reject;
import com.sinsiway.intern.service.DbService;
import com.sinsiway.intern.service.LogService;
import com.sinsiway.intern.service.RejectService;
import com.sinsiway.intern.util.ClientIp;
import com.sinsiway.intern.service.ConnectService;

@RestController
public class ConnectController {

	@Autowired
	ConnectService service;
	@Autowired
	LogService logService;
	@Autowired
	RejectService rejService;
	@Autowired
	DbService dbService;
	@Autowired
	HttpSession session;

	static int cnt = 0;

	// 접속
	@GetMapping("/connect/{database_id}")
	public Map<String, String> connect(@PathVariable long database_id, HttpServletRequest request) {
		/////////////////////////// 객체 선언, 초기화
		// 접속 수행 결과 저장(return)
		String result = "";
		// 접속 수행 성공 여부 저장
		boolean success = true;
		// connectlog
		ConnectionLog clog = new ConnectionLog();
		// connectlog 입력 성공 여부
		String token_id = "";
		// connection 객체 선언, 초기화
		Connection con = null;
		// response json
		Map<String, String> response = new HashMap<String, String>();

		/////////////////////////// client_ip 받아 reject 테이블에 있는지 확인
		// ip 받기
		String ip = ClientIp.getIp(request);
		System.out.println(ip);
		// where = ip로 검사하여 reject 객체 받아오기
		Object rej = rejService.rejectFindOne(ip);
		
		// client_ip가 sw_database_reject에 있는지 검사
		if (rej == null) { // 없으면 : result "접속 성공"
			try {
				// connect 시도
				Db db = (Db) dbService.findOne(database_id); // 해당 id에 저장된 db 정보 불러오기
				con = service.connect(db); // connection 객체 받아오기
				// 토큰 발행
				cnt++;
				session.setAttribute("token" + database_id + cnt, cnt); // token21, 1
				session.setAttribute("token" + database_id + cnt + "con", con); // token21con, con
				session.setAttribute("token" + database_id + cnt + "dbId", database_id);
				result = "접속 성공";
				token_id = database_id + "" + session.getAttribute("token" + database_id + cnt);
			} catch (Exception e) {
				// TODO: handle exception
				result = (String) dbService.findOne(database_id);
			}
			
		} else { // 있다면 : result "접속 실패"
			result = "접속 제한된 ip입니다.";
			success = false;
		}

		//////////////////////////// connection log 저장
		// 접속 시간
		Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		// 접속 로그 dto
		clog.setClient_ip(ip);
		clog.setConnect_date(now);
		clog.setDatabase_id(database_id);
		clog.setResult(success);
		// 접속 로그 저장
		String logResult = logService.connectLog(clog);

		// map 저장
		response.put("result", result);
		response.put("token_id", token_id);
		response.put("log_result", logResult);
		return response;
	}

	// 접속 종료
	@GetMapping("/close/{token}")
	public Map<String, String> close(@PathVariable int token) {
		Connection con = (Connection) session.getAttribute("token" + token + "con"); // token21con
		service.close(con);
		Map<String, String> response = new HashMap<String, String>();
		response.put("result", "접속 종료");
		return response;
	}

}

package com.sinsiway.intern.controller;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sinsiway.intern.dto.Dept;
import com.sinsiway.intern.dto.Emp;
import com.sinsiway.intern.dto.ExecuteLog;
import com.sinsiway.intern.dto.Query;
import com.sinsiway.intern.service.LogService;
import com.sinsiway.intern.service.QueryService;

@RestController
public class QueryController {

	@Autowired
	QueryService service;
	@Autowired
	LogService logSerivce;

	@PostMapping("/equery")
	public Object selectE(@RequestBody Query query, HttpSession session) {
		Connection con = (Connection) session.getAttribute("token" + query.getToken() + "con");
		String type = query.getSql().split(" ")[0];
		
		System.out.println("sql : " + query.getSql());
		System.out.println("token : " + query.getToken());
		Object resp = service.selectE(con, query.getSql(), type);

		// 수행 로그 테이블 입력
		ExecuteLog elog = new ExecuteLog();
		// 수행 일시
		elog.setExec_date(Timestamp.valueOf(LocalDateTime.now()));
		// database_id
		elog.setDatabase_id((long)session.getAttribute("token"+query.getToken()+"dbId"));
		// 클라이언트 IP
		elog.setClient_ip("101");
		// 쿼리 문장
		elog.setSql_text(query.getSql());
		// 수행 타입
		elog.setSql_type(type);
		// 문장 수행 결과 : 일단 true
		elog.setResult(true);
		// 수행 메시지 : 세션에 메시지 저장하고 가져오는 방법 => execute일 때, update일 때 나눠야 함
		if (type.equals("select")) {
			elog.setMessage(((List<Object>) resp).size()+"개가 조회되었습니다.");
		} else {
			elog.setMessage((String)resp);
		}
		
		// 로그 입력 서비스 호출
		logSerivce.executeLog(elog);
		
		return resp;
	}

	@PostMapping("/dquery")
	public List<Dept> selectD(@RequestBody Query query, HttpSession session) {
		Connection con = (Connection) session.getAttribute("token" + query.getToken() + "con");
		System.out.println("sql : " + query.getSql());
		System.out.println("token : " + query.getToken());
		return service.selectD(con, query.getSql(), query.getSql().split(" ")[0]); // 쿼리 타입을 입력하는 대신 sql에서 split하는 방법
	}
}

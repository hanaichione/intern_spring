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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sinsiway.intern.dto.Dept;
import com.sinsiway.intern.dto.Emp;
import com.sinsiway.intern.dto.ExecuteLog;
import com.sinsiway.intern.dto.Query;
import com.sinsiway.intern.service.LogService;
import com.sinsiway.intern.service.QueryService;
import com.sinsiway.intern.util.ClientIp;

@RestController
public class QueryController {

	@Autowired
	QueryService service;
	@Autowired
	LogService logSerivce;

	@PostMapping("/querys")
	public Object select(@RequestBody Query query, HttpSession session, HttpServletRequest request) {
		Connection con = (Connection) session.getAttribute("token" + query.getToken() + "con");
		String type = query.getSql().split(" ")[0];
		boolean result = true;
		Map<String, Object> response = new HashMap<String, Object>();
		
		System.out.println("sql : " + query.getSql());
		System.out.println("token : " + query.getToken());
		Object resp = service.select(con, query.getSql(), type);

		// 수행 로그 테이블 입력
		ExecuteLog elog = new ExecuteLog();
		// 수행 일시
		elog.setExec_date(Timestamp.valueOf(LocalDateTime.now()));
		// database_id
		elog.setDatabase_id((long)session.getAttribute("token"+query.getToken()+"dbId"));
		// 클라이언트 IP
		String ip = ClientIp.getIp(request);
		elog.setClient_ip(ip);
		// 쿼리 문장
		elog.setSql_text(query.getSql());
		// 수행 타입
		elog.setSql_type(type);
		// 수행 메시지 : 세션에 메시지 저장하고 가져오는 방법 => execute일 때, update일 때 나눠야 함
		try {
			System.out.println(resp);
			elog.setMessage((String) resp);
			result = false;
		} catch (Exception e) {
			// TODO: handle exception
			if (type.equalsIgnoreCase("select")) {
				elog.setMessage(((List<Object>) resp).size()+"개가 조회되었습니다.");
			} else if (type.equalsIgnoreCase("insert") || type.equalsIgnoreCase("delete") || type.equalsIgnoreCase("update")) {
				elog.setMessage((String)resp);
			}
		}

		// 문장 수행 결과 : 일단 true
		elog.setResult(result);
		
		// 로그 입력 서비스 호출
		logSerivce.executeLog(elog);
		
		response.put("result", resp);
		
		return response;
	}

//	@PostMapping("/dquery")
//	public List<Dept> selectD(@RequestBody Query query, HttpSession session) {
//		Connection con = (Connection) session.getAttribute("token" + query.getToken() + "con");
//		System.out.println("sql : " + query.getSql());
//		System.out.println("token : " + query.getToken());
//		return service.selectD(con, query.getSql(), query.getSql().split(" ")[0]); // 쿼리 타입을 입력하는 대신 sql에서 split하는 방법
//	}
}

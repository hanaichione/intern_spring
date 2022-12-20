package com.sinsiway.intern.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sinsiway.intern.DTO.User;
import com.sinsiway.intern.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	HttpSession session;

	// 추후에 전체 database_id를 가져와 담아놓은 걸로 바꾸기
	// 혹은 연결 후 database_id 검사하는 service, dao 만들기
	ArrayList<Long> id = new ArrayList<Long>();

	// 접속
	@GetMapping("/connect")
	public String connect() {
		Connection con = null;
		con = service.connect();
		System.out.println("controller에서 호출 성공");
		session.setAttribute("con", con);
		System.out.println("session에 저장 성공");
		
		// session에 저장할 database_id 만들기
		int i = 0;
		Random rand = new Random();
		do {
			long database_id = rand.nextLong();
			boolean check = false; // 중복 없을 때
			for (int j = 0; j < i; j++) {
				if (database_id == id.get(j)) {
					check = true;
					break;
				}
			}
			if (check)
				i--;
			else {
				id.add(database_id);
				session.setAttribute("database_id", database_id);
				i++;
			}
		} while (i < id.size());

		return "접속 성공" + session.getAttribute("database_id");
	}

	// 접속 종료
	@GetMapping("/close")
	public String close() {
		Connection con = (Connection) session.getAttribute("con");
		service.close(con);
		session.invalidate();
		return "접속 종료";
	}

	@PostMapping("/insertUser")
	public String insert(@RequestBody User user) {
		Connection con = (Connection) session.getAttribute("con");
		try {
			int n = (int)session.getAttribute("insert");
			return "해당 세션 아이디로 생성된 접속 정보가 이미 있습니다. database_id : "+session.getAttribute("database_id");
		} catch (Exception e) {
			// TODO: handle exception
			long database_id = (long) session.getAttribute("database_id");
			service.insert(con, database_id, user.getType(), user.getIp(), user.getPort(), user.getDatabase(),
					user.getUsername(), user.getPassword());
			session.setAttribute("insert", 1);
		} 
		return "입력 성공";

	}

	@GetMapping("/findAllUser")
	public List<User> findAll() {
		Connection con = (Connection) session.getAttribute("con");
		List<User> user = service.findAll(con);
		return user;
	}

	@GetMapping("/findOneUser/{database_id}")
	public User findOne(@PathVariable("database_id") Long database_id) {
		Connection con = (Connection) session.getAttribute("con");
		User user = service.findOne(con, database_id);
		return user;
	}

	@DeleteMapping("deleteUser/{database_id}")
	public void delete(@PathVariable("database_id") Long database_id) {
		Connection con = (Connection) session.getAttribute("con");
		service.delete(con, database_id);
		session.setAttribute("database_id", null);
	}

	@PutMapping("/updateUser/{database_id}")
	public void update(@RequestBody User user, @PathVariable("database_id") Long database_id) {
		Connection con = (Connection) session.getAttribute("con");
		service.update(con, database_id, user.getType(), user.getIp(), user.getPort(), user.getDatabase(),
				user.getUsername(), user.getPassword());
	}
}

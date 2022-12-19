package com.sinsiway.intern.controller;

import java.util.List;

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
	private HttpSession session;
	
	@PostMapping("/insertUser")
	public String insert(@RequestBody User user) {
		try {
			long database_id = (long) session.getAttribute("database_id");
			return "세션에 아이디가 있습니다.";
		} catch (Exception e) {
			// TODO: handle exception
			// 세션에 아이디가 없다 == insert 한 적이 없다
			service.insert(user.getDatabase_id(), user.getType(), user.getIp(), user.getPort(), user.getDatabase(),
					user.getUsername(), user.getPassword());
			session.setAttribute("database_id", user.getDatabase_id());
			return "입력 성공";
		}
	}

	@GetMapping("/findAllUser")
	public List<User> findAll() {
		List<User> user = service.findAll();
		return user;
	}

	@GetMapping("/findOneUser/{database_id}")
	public User findOne(@PathVariable("database_id") Long database_id) {
		User user = service.findOne(database_id);
		return user;
	}

	@DeleteMapping("deleteUser/{database_id}")
	public void delete(@PathVariable("database_id") Long database_id) {
		service.delete(database_id);
		session.setAttribute("database_id", null);
	}

	@PutMapping("/updateUser/{database_id}")
	public void update(@RequestBody User user) {
		service.update(user.getDatabase_id(), user.getType(), user.getIp(), user.getPort(), user.getDatabase(),
				user.getUsername(), user.getPassword());
	}
}

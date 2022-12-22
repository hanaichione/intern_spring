package com.sinsiway.intern.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Db {
	private long database_id;
	private int type;
	private String ip;
	private int port;
	private String database;
	private String username;
	private String password;

	public Db() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Db(long database_id, int type, String ip, int port, String database, String username, String password) {
		super();
		this.database_id = database_id;
		this.type = type;
		this.ip = ip;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
	}
	
}

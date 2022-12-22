package com.sinsiway.intern.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConnectionLog {
	private long id;
	private long database_id;
	private String client_ip;
//	private java.sql.Date connect_date;
	private java.sql.Timestamp connect_date;
	private boolean result;
	
	public ConnectionLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConnectionLog(long id, long database_id, String client_ip, Timestamp connect_date, boolean result) {
		super();
		this.id = id;
		this.database_id = database_id;
		this.client_ip = client_ip;
		this.connect_date = connect_date;
		this.result = result;
	}
	
	public boolean getResult() {
		return this.result;
	}
}

package com.sinsiway.intern.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ExecuteLog {
	private long id;
	private long database_id;
	private String client_ip;
	private java.sql.Timestamp exec_date;
	private String sql_text;
	private String sql_type;
	private boolean result;
	private String message;
	
	public boolean getResult() {
		return this.result;
	}
	
	public ExecuteLog() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public ExecuteLog(long id, long database_id, String client_ip, Timestamp exec_date, String sql_text,
			String sql_type, boolean result, String message) {
		super();
		this.id = id;
		this.database_id = database_id;
		this.client_ip = client_ip;
		this.exec_date = exec_date;
		this.sql_text = sql_text;
		this.sql_type = sql_type;
		this.result = result;
		this.message = message;
	}

}
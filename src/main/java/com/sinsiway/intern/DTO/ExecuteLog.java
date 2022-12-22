package com.sinsiway.intern.dto;

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
}

package com.sinsiway.intern.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Query {
	int token;
	String type;
	String sql;
	
	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Query(int token, String type, String sql) {
		super();
		this.token = token;
		this.type = type;
		this.sql = sql;
	}
}

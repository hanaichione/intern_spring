package com.sinsiway.intern.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Reject {
	private long policy_id;
	private long database_id;
	private String client_ip;
	
	public Reject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reject(long policy_id, long database_id, String client_ip) {
		super();
		this.policy_id = policy_id;
		this.database_id = database_id;
		this.client_ip = client_ip;
	}
	
}

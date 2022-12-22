package com.sinsiway.intern.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Dept {
	private int deptno;
	private String dname;
	private String loc;
	
	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dept(int deptno, String dname, String loc) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}
	
}

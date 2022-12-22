package com.sinsiway.intern.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Emp {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private java.sql.Date hiredate;
	private double sal;
	private double comm;
	private int deptno;
	
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emp(int empno, String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}
}

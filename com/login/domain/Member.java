package com.login.domain;

public class Member 
{
	private int num;
	private String name;
	private String phone;
	private String addr;
	private String password;
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Member(int num, String name, String phone, String addr, String password)
	{
		setNum(num);
		setName(name);
		setPhone(phone);
		setAddr(addr);
		setPassword(password);
	}
}

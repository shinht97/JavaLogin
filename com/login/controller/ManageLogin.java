package com.login.controller;

import java.util.ArrayList;

import com.login.domain.Member;

public interface ManageLogin 
{
	abstract ArrayList<String> readMember(ArrayList<Member> members);
	
	abstract void updateMember(ArrayList<Member> members, int num, String name, String phone, String addr);
	
	abstract void deleteMember(ArrayList<Member> members, int num);
	
	abstract ArrayList<Member> readFileMember(String _filePath);
	
	abstract void writeFileMember(ArrayList<Member> members);
}

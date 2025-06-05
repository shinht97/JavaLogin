package com.member.controller;

import java.util.ArrayList;
import com.member.domain.*;

public interface ManageMember 
{
	public void createMember(ArrayList<Member> members, String name, String phone, String addr, String passwd);
	
	public Member readMember(ArrayList<Member> members, int num);
	
	public Member readMember(ArrayList<Member> members, String target_name);
	
	public boolean updateMember(ArrayList<Member> members, int num, String name, String phone, String addr);
	
	public boolean deleteMember(ArrayList<Member> members, int num);
	
	public boolean fileMember(ArrayList<Member> members);
	
	public void listMember(ArrayList<Member> members);
}

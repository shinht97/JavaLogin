package com.member.controller;

import com.member.domain.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Controller implements ManageMember
{
	int member_cnt = 0;
	
	public void createAdmin(ArrayList<Member> members)
	{
		members.add(new Member(0, "admin", "01011112222", "관리자 주소", "admin"));
		
		this.member_cnt++;
	}
	
	public void createMember(ArrayList<Member> members, String name, String phone, String addr, String passwd)
	{
		members.add(new Member(this.member_cnt, name, phone, addr, passwd));
		member_cnt++;
	}
	
	public Member readMember(ArrayList<Member> members, int num)
	{
		return members.get(num);
	}
	
	public Member readMember(ArrayList<Member> members, String target_name)
	{
		for (int i = 0; i < members.size(); i++)
		{
			if (members.get(i).getName().equals(target_name))
			{
				return members.get(i);
			}
		}
		
		return null;
	}
	
	public boolean updateMember(ArrayList<Member> members, int num, String name, String phone, String addr)
	{
		try
		{
			members.get(num).setName(name);
			members.get(num).setPhone(phone);
			members.get(num).setAddr(addr);
			
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public boolean deleteMember(ArrayList<Member> members, int num)
	{
		members.remove(num);
		
		return true;
	}
	
	public boolean fileMember(ArrayList<Member> members)
	{
		File file = new File("./memberList.txt");
		
		try
		{
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			
			for (int i = 0; i < members.size(); i++)
			{
				fw.write("회원번호: " + members.get(i).getNum() + 
						"    이름: " + members.get(i).getName() +
						"    연락처: " + members.get(i).getPhone() +
						"    주소: " + members.get(i).getAddr() + 
						"    비밀번호: " + members.get(i).getPassword() + "\n");
			}
			
			fw.close();
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	
	public void listMember(ArrayList<Member> members)
	{
		if (members.size() > 1)
		{
			for (int i = 1; i < members.size(); i++)
			{
				System.out.println("회원 번호 " + members.get(i).getNum() + "    " + 
									"이름 " + members.get(i).getName() + "    " + 
									"연락처 " + members.get(i).getPhone()); // print member info
			}
		}
		else
		{
			System.out.println("회원 정보가 없습니다.\n");
		}
	}
}

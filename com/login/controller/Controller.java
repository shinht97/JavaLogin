package com.login.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.login.domain.Member;

public class Controller implements ManageLogin
{
	public ArrayList<String> readMember(ArrayList<Member> members)
	{
		ArrayList<String> member_lst = new ArrayList<String>();
		
		for (int i = 1; i < members.size(); i++)
		{
			member_lst.add(members.get(i).getName());
		}
		
		return member_lst;
	}
	
	public void updateMember(ArrayList<Member> members, int num, String name, String phone, String addr)
	{
		members.get(num).setName(name);
		members.get(num).setPhone(phone);
		members.get(num).setAddr(addr);
	}
	
	public void deleteMember(ArrayList<Member> members, int num)
	{
		members.remove(num);
	}
	
	public ArrayList<Member> readFileMember(String _filePath) 
	{
		ArrayList<Member> return_list = new ArrayList<Member>();
		
		File file = new File(_filePath);
		
		if(!file.exists())
		{
			System.out.println("회원 정보를 저장한 파일이 없습니다.");
			
			return null;
		}
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line;
			
			while ((line = reader.readLine()) != null)
			{
				String[] sentence = line.split("\n");
				
				for (String str : sentence)
				{					
					String[] infos = str.split("    ");
					
					int num = Integer.parseInt(infos[0].split(": ")[1]);
					String name = infos[1].split(": ")[1];
					String phone = infos[2].split(": ")[1];
					String addr = infos[3].split(": ")[1];
					String passwd = infos[4].split(": ")[1];
					
					return_list.add(new Member(num, name, phone, addr, passwd));
				}
			}
			
			reader.close();
			
			return return_list;			
		}
		catch (Exception e)
		{
			System.out.println(e);
		
			return null;
		}
	}
	
	public void writeFileMember(ArrayList<Member> members)
	{
		File file = new File("./memberList.txt");
		
		try
		{			
			FileWriter fw = new FileWriter(file);
			
			for (int i = 0; i < members.size(); i++)
			{
				fw.write("회원번호: " + members.get(i).getNum() + 
						"\t 이름: " + members.get(i).getName() +
						"\t 연락처: " + members.get(i).getPhone() +
						"\t 주소: " + members.get(i).getAddr() + 
						"\t 비밀번호: " + members.get(i).getPassword() + "\n");
			}
			
			fw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

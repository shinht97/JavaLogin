package com.login.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.login.domain.Member;

public class LoginController extends Controller
{
	public String Login(Scanner s, Controller control, ArrayList<Member> members, ArrayList<String> id_lst)
	{
		int login_cnt = 0;
		String id = "";
		String pw = "";
		
		while (true)
		{
			System.out.println("*".repeat(50));
			System.out.println("\t\t\t로그인\t\t");
			System.out.println("*".repeat(50));
			
			System.out.print("아이디를 입력하세요: ");
			id = s.nextLine();
			
			if (!id_lst.contains(id))
			{
				System.out.println("일치하는 회원이 없습니다.");
				login_cnt++;
			}
			else
			{
				int mem_idx = id_lst.indexOf(id) + 1;
				
				System.out.print("비밀번호를 입력하세요: ");
				
				pw = s.nextLine();
				
				if (!pw.equals(members.get(mem_idx).getPassword()))
				{
					System.out.println("비밀번호가 틀렸습니다.");
					login_cnt++;
				}
				else
				{
					System.out.println("로그인 성공");
					return id;
				}
			}
			
			
			if (login_cnt == 3)
			{
				return null;
			}
		}
	}
	
	public void memberInfo(ArrayList<Member> members, ArrayList<String> id_lst, String user_id)
	{
		int member_num = id_lst.indexOf(user_id) + 1;
		
		System.out.println("회원번호 " + members.get(member_num).getNum() + "    " + 
							"이름 " + members.get(member_num).getName() + "    " + 
							"연락처 " + members.get(member_num).getPhone() + "    " + 
							"주소 " + members.get(member_num).getAddr());
	}
	
	public void memberUpdate(Scanner s, Controller control, ArrayList<Member> members, ArrayList<String> id_lst, String user_id)
	{
		int member_num = id_lst.indexOf(user_id) + 1;
		
		System.out.print(user_id + " 회원의 이름을 수정하세요 : ");
		
		String changed_name = s.nextLine();
		
		if (changed_name.isEmpty())
		{
			changed_name = members.get(member_num).getName();
		}
		
		System.out.print(user_id + " 회원의 연락처를 수정하세요 : ");
		
		String changed_phone = s.nextLine();
		
		if (changed_phone.isEmpty())
		{
			changed_phone = members.get(member_num).getPhone();
		}
		
		System.out.print(user_id + " 회원의 주소를 수정하세요 : ");
		
		String changed_addr = s.nextLine();
		
		if (changed_addr.isEmpty())
		{
			changed_addr = members.get(member_num).getAddr();
		}
		
		System.out.print(user_id + " 회원의 비밀번호를 입력하세요 : ");
		
		String mem_pw = s.nextLine();
		
		if (mem_pw.equals(members.get(member_num).getPassword()))
		{
			control.updateMember(members, member_num, changed_name, changed_phone, changed_addr);
		
			control.writeFileMember(members);
			
			System.out.println("회원 정보가 수정 되었습니다.");
		}
		else
		{
			System.out.println("비밀번호가 틀립니다.");
		}
	}
	
	public boolean memberDelete(Scanner s, Controller control, ArrayList<Member> members, ArrayList<String> id_lst, String user_id)
	{
		int member_num = id_lst.indexOf(user_id) + 1;
		
		System.out.print("비밀번호를 입력히세요 : ");
		
		String mem_pw = s.nextLine();
		
		if(mem_pw.equals(members.get(member_num).getPassword()))
		{
			members.remove(member_num);
			
			control.writeFileMember(members);
			
			return true;
		}
		else
		{
			System.out.println("비밀번호가 틀립니다.");
			
			return false;
		}
	}
}

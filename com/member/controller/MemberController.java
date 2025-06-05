package com.member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.member.domain.Member;

public class MemberController extends Controller
{
	public boolean Login(Scanner s, ArrayList<Member> members)
	{	
		int login_try_cnt = 0;
		
		String admin_id = "";
		String admin_pw = "";
		
		while (true)
		{
			System.out.print("\r");
			System.out.println("*".repeat(50));
			System.out.println("\t\t로그인\t\t");
			System.out.println("*".repeat(50));
			
			System.out.print("아이디를 입력하세요: ");
			admin_id = s.nextLine();
			
			if (!admin_id.equals(members.get(0).getName()))
			{
				System.out.println("일치하는 아이디가 없습니다.");
				login_try_cnt++;
				continue;
			}
			
			System.out.print("비밀번호를 입력하세요: ");
			admin_pw = s.nextLine();
			
			if (login_try_cnt == 3)
			{
				System.out.println("로그인 횟수 초과 \n");
								
				return false;
			}
			
			if (admin_id.equals(members.get(0).getName()) && admin_pw.equals(members.get(0).getPassword()))
			{
				System.out.println("로그인 성공 \n");
				return true;
			}
			else if (admin_id.equals(members.get(0).getName()) && !admin_pw.equals(members.get(0).getPassword()))
			{
				System.out.println("비밀번호가 틀렸습니다.");
				login_try_cnt++;
			}
			
		}
	}
	
	public void createMember(Scanner s, Controller control, ArrayList<Member> members)
	{
		ArrayList<String> member_name = new ArrayList<String>();
		
		for (int i = 0; i < members.size(); i++)
		{
			member_name.add(members.get(i).getName());
		}
		
		System.out.print("등록 하실 회원의 이름을 입력하세요 : ");
		String mem_name = s.nextLine();
		
		System.out.print("등록 하실 회원의 연락처를 입력하세요 : ");
		String mem_phone = s.nextLine();
		
		System.out.print("등록 하실 회원의 주소를 입력하세요 : ");
		String mem_addr = s.nextLine();
		
		System.out.print("등록 하실 회원의 비밀번호를 입력하세요 : ");
		String mem_passwd = s.nextLine();
		
		
		if (member_name.contains(mem_name))
		{
			System.out.println("이미 존재하는 이름이비니다. 다른 이름으로 등록해주세요");
			
			this.createMember(s, control, members);
		}
		else
		{
			control.createMember(members, mem_name, mem_phone, mem_addr, mem_passwd);
			
			System.out.println("회원 등록 성공");
		}
	}
	
	public void readMember(Scanner s, Controller control, ArrayList<Member> members)
	{
		ArrayList<String> member_name = new ArrayList<String>();
		
		for (int i = 0; i < members.size(); i++)
		{
			member_name.add(members.get(i).getName());
		}
		
		System.out.print("조회할 회원 이름을 입력하세요 ");
		String target_name = s.nextLine();
		
		if (!member_name.contains(target_name))
		{
			System.out.println("일치하는 이름이 없습니다.");
		}
		else
		{
			Member readed_member = control.readMember(members, target_name);
			
			System.out.println("회원 번호 : " + readed_member.getNum() + "\t" + 
								"회원 이름 : " + readed_member.getName() + "\t" +
								"회원 연락처 : " + readed_member.getPhone() + "\t" +
								"회원 주소 : " + readed_member.getAddr());
		}
	}
	
	public void updateMember(Scanner s, Controller control, ArrayList<Member> members)
	{
		System.out.print("수정하실 회원의 이름을 입력하세요 : ");
		String mem_name = s.nextLine();
		
		if (mem_name.isEmpty())
		{
			System.out.println("수정할 회원이 선택되지 않았습니다.\n수정을 종료합니다.");
			return;
		}
		
		System.out.print(mem_name + " 회원의 이름을 수정하세요 : ");
		String changed_name = s.nextLine();
		
		System.out.println("입력 : " + changed_name);
		
		if (changed_name.isEmpty())
		{
			changed_name = control.readMember(members, mem_name).getName();
		}
		
		System.out.print(mem_name + " 회원의 연락처를 수정하세요 : ");
		
		String changed_phone = s.nextLine();
		
		System.out.println("입력 : " + changed_phone);
		
		if (changed_phone.isEmpty())
		{
			changed_phone = control.readMember(members, mem_name).getPhone();
		}
		
		System.out.print(mem_name + " 회원의 주소를 수정하세요 : ");
		
		String changed_addr = s.nextLine();
		
		System.out.println("입력 : " + changed_addr);
		
		if (changed_addr.isEmpty())
		{
			changed_addr = control.readMember(members, mem_name).getAddr();
		}
		
		System.out.print(mem_name + " 비밀번호를 입력하세요 : ");
		
		String mem_passwd = s.nextLine();
		
		if (mem_passwd.equals(control.readMember(members, mem_name).getPassword()))
		{
			boolean update_res = control.updateMember(members, control.readMember(members, mem_name).getNum(), changed_name, changed_phone, changed_addr);
		
			if (update_res)
			{
				System.out.println("업데이트 성공");
			}
			else
			{
				System.out.println("업데이트 실패");
			}
		}
		else
		{
			System.out.println("회원 정보 수정 실패");
		}
	}
	
	public void deleteMember(Scanner s, Controller control, ArrayList<Member> members)
	{
		System.out.print("삭제 할 회원 이름을 입력하세요 ");
		
		String target_name = s.nextLine();
		
		Member selected_member = control.readMember(members, target_name);
		
		System.out.print("비밀번호를 입력하세요 ");
		
		String input_password = s.nextLine();
		
		if (input_password.equals(selected_member.getPassword()))
		{
			if(control.deleteMember(members, selected_member.getNum()))
			{
				System.out.println("삭제되었습니다.");
			}
			else
			{
				System.out.println();
			}
		}
		else
		{
			System.out.println("비밀번호가 틀려 삭제 하지 않습니다.");
		}
	}
	
	public void fileMember(Controller control, ArrayList<Member> members)
	{
		if(control.fileMember(members))
		{
			System.out.println("파일 저장 성공");
		}
		else
		{
			System.out.println("파일 저장 실패");
		}
	}
}

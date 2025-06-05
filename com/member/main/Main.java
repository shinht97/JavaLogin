package com.member.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.member.controller.*;
import com.member.domain.*;

public class Main
{
	public void ShowMenu()
	{		
		System.out.println("*".repeat(50));
		System.out.println("\t\t회원 관리 프로그램\t\t");
		System.out.println("*".repeat(50));
	
		System.out.println("1. 고객 정보 등록하기 \t 2. 고객 정보 조회하기");
		System.out.println("3. 고객 정보 수정하기 \t 4. 고객 정보 삭제하기");
		System.out.println("5. 고객 정보 목록보기 \t 6. 고객 정보 파일");
		System.out.println("7. 종료");
		
		System.out.println("*".repeat(50));
		
		System.out.print("메뉴 번호를 선택해주세요 ");
	}
		
	public static void main(String[] args)
	{
		ArrayList<Member> members = new ArrayList<Member>();
		
		Scanner s = new Scanner(System.in);
		
		Main main = new Main();
		
		MemberController control = new MemberController();
		
		int menu = 0;
		
		if (members.size() == 0)
		{
			control.createAdmin(members);
		}
				
		if (!control.Login(s, members))
		{
			System.out.println("프로그램을 종료합니다.");
			return;
		}
				
		while (true)
		{
			main.ShowMenu();
			
			menu = Integer.parseInt(s.nextLine());
			try
			{
				switch (menu)
				{
					case 1:
						control.createMember(s, control, members);
						break;
						
					case 2:
						control.readMember(s, control, members);
						break;
						
					case 3:
						control.updateMember(s, control, members);
						break;
						
					case 4:
						control.deleteMember(s, control, members);
						break;
						
					case 5:
						control.listMember(members);
						break;
						
					case 6:
						control.fileMember(control, members);
						break;
						
					case 7:
						System.out.println("회원 관리 프로그램을 종료 합니다.\n");
						
						s.close();
						
						return;
				}
			}
			catch(Exception e)
			{
				System.out.println("1부터 7까지의 숫자를 입력하세요.");
			}
			
		}
	}
}

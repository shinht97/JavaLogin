package com.login.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.login.domain.Member;
import com.login.controller.*;

public class Main 
{
	public void ShowMenu(String _id)
	{
		System.out.println("*".repeat(50));
		System.out.println("\033[F\t\t" + _id + "님 안녕하세요?\t\t");
		System.out.println("*".repeat(50));
	
		System.out.println("1. 회원 정보 확인하기        2. 회원 정보 수정하기");
		System.out.println("3. 회원 탈퇴              4. 종료");
		
		System.out.println("*".repeat(50));
		
		System.out.print("메뉴 번호를 선택해주세요 ");
	}
	
	public static void main(String[] args) 
	{
		ArrayList<Member> members = new ArrayList<Member>();
		
		Main main = new Main();
		LoginController control = new LoginController();
		
		int menu = 0;
		
		String filePath = "./memberList.txt";
		
		Scanner s = new Scanner(System.in);
		
		members = control.readFileMember(filePath);
		
		ArrayList<String> id_lst = control.readMember(members);
		
		String user_id = control.Login(s, control, members, id_lst);
		
		if (user_id.isEmpty())
		{
			System.out.println("로그인 횟수 초과");
			return;
		}
		
		while (true)
		{
			main.ShowMenu(user_id);
			
			menu = Integer.parseInt(s.nextLine());
			
			try
			{
				switch (menu)
				{
					case 1:
						control.memberInfo(members, id_lst, user_id);
						break;
						
					case 2:
						control.memberUpdate(s, control, members, id_lst, user_id);
						break;
						
					case 3:
						if(control.memberDelete(s, control, members, id_lst, user_id))
						{
							System.out.println("로그인 프로그램을 종료 합니다.");
							return;
						}
						else
						{
							
						}
						
						break;
												
					case 4:
						System.out.println("종료 합니다.\n");
						
						s.close();
						
						return;
				}
			}
			catch(Exception e)
			{
				System.out.println("1부터 4까지의 숫자를 입력하세요.");
			}
		}
	}
}

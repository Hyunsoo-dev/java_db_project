package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import memberDao.MemberDAO;
import memberDto.MemberDTO;

public class Main {

	public static void main(String[] args) throws IOException {
		MemberDAO dao = new MemberDAO(); //연결
	
		

		while(true) {

			System.out.print("1.전체 친구 조회 2.친구 입력 3.친구 조회 4.친구 수정  5.삭제  6.종료" );

			int select = System.in.read() -48;

			while(System.in.read() != '\n') { }

			
			switch(select) {

			case 1: dao.select(); break;
			case 2 : dao.insert(); break;
			case 3 :dao.view();	break;
			case 4 : dao.update(); break;
			case 5 : dao.delete();break;
			case 6 : dao.exit(); break;
			}
			
		}
			
		
	}

}


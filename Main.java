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
		MemberDAO dao = new MemberDAO(); //����
	
		

		while(true) {

			System.out.print("1.��ü ģ�� ��ȸ 2.ģ�� �Է� 3.ģ�� ��ȸ 4.ģ�� ����  5.����  6.����" );

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


package TestFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Insert {
	
	
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "system";
	String pass = "oracle";
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con;
	
	public void getCon() { //DB�� �����ϴ� �޼ҵ�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
		}catch(Exception e) {
			
		}
	}
	
	
	public void ins() { /////////insert 
		getCon();
		try {
			int hakbum;
			String name;
			int hakjum;
			String addr1;
			String addr2;
			String email;
			int type;
			
			
			
			Scanner s = new Scanner(System.in);
			System.out.println("����ϰ��� �ϴ� �й��� �Է��ϼ���.");
			hakbum = s.nextInt();
			s.nextLine();
			System.out.println("�̸��� �Է��ϼ���");
			name = s.nextLine();
			System.out.println("������ �Է��ϼ���");
			hakjum = s.nextInt();
			s.nextLine();
			System.out.println("�ּ�1�� �Է��ϼ���");
			addr1 = s.nextLine();
			System.out.println("�ּ�2�� �Է��ϼ���");
			addr2 = s.nextLine();
			System.out.println("�̸����� �Է��ϼ���.");
			email = s.nextLine();
		
			String sql = "insert into test values (?,?,?,?,?,?)";
					//testMember �� �÷�(�ʵ�)�� ������ŭ ? ��ȣ�� ��������.
			ps = con.prepareStatement(sql);
			ps.setInt(1, hakbum);
			ps.setString(2, name);
			ps.setInt(3, hakjum);
			ps.setString(4,addr1);
			ps.setString(5, addr2);
			ps.setString(6, email);
			ps.executeUpdate();
			System.out.println("insert ����");
				
		}catch(Exception e) {
				System.out.print("insert ����");
		}
		
	
	}
	
	
	
	
	public void sel() { ///////select 
		getCon();
		try {
			int hakbum;
			String name;
			int hakjum;
			String addr1;
			String addr2;
			String email;
			int type;
			
			Scanner s = new Scanner(System.in);
			System.out.println("��ȸ�ϰ��� �ϴ� ������ �̸��� �Է��ϼ���");
			name = s.nextLine();
			String sql = "select * from test where name =?";
			ps = con.prepareStatement(sql);
			ps.setString(1,name);
			rs = ps.executeQuery();
			while(rs.next()) {
			hakbum = rs.getInt(1);
			name = rs.getString(2);
			hakjum = rs.getInt(3);
			addr1 = rs.getString(4);
			addr2 = rs.getString(5);
			email = rs.getString(6);
			System.out.println(hakbum + "\t" + name + "\t" + hakjum + "\t" + addr1 + "\t" + addr2 + "\t" + email);
			}
			System.out.println("select ����");
		}catch(Exception e) {
			System.out.println("select ����");
		}
		
		
	}
	
	public void del() { ////////delete 
		getCon();
		try {
			int hakbum = 0;
			String name = null;
			int hakjum = 0;
			String addr1;
			String addr2;
			String email;
			int type = 0;
			
			Scanner s = new Scanner(System.in);
			System.out.println("���� �˻� ���� :�й��� 1 �Է�, �̸��� 2 �Է�--�� �� �Ѱ����� �����ϼ���.");
			type = s.nextInt();
			s.nextLine();
			if(type == 1) {
				System.out.println("�����ϰ��� �ϴ� ������ �й��� �Է��ϼ���.");
				hakbum = s.nextInt();
				s.nextLine();  
			}else if(type == 2) {
				System.out.println("�����ϰ��� �ϴ� ������ �̸��� �Է��ϼ���.");
				name = s.nextLine();
			}
			
			
			if(type == 1) {
				String sql = "delete from test where hakbum =?";
				ps = con.prepareStatement(sql);
			
				ps.setInt(1, hakbum);
				ps.executeUpdate();
				System.out.println("�й� "+ hakbum  + "�� �� ������ �����Ͽ����ϴ�.");
			}else if(type ==2) {
			
				String sql = "delete from test where name =?";
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.executeUpdate();
				System.out.println(name + "�� ������ �����Ͽ����ϴ�.");
			}
				System.out.println("delete����");
			
		}catch(Exception e) {
			System.out.println("delete ����");
		}
		
	}
	
	public void up() { //////////update
	getCon();
		
	try {
			
			int hakbum = 0;
			String name = null;
			int hakjum = 0;
			String addr1;
			String addr2;
			String email;
			int type = 0;
			
			Scanner s = new Scanner(System.in);
				System.out.println("���� �˻� ���� :�й��� 1 �Է�, �̸��� 2 �Է�--�� �� �Ѱ����� �����ϼ���.");
				type = s.nextInt();
				s.nextLine();
				if(type == 1) {
					System.out.println("�����ϰ��� �ϴ� ������ �й��� �Է��ϼ���.");
					hakbum = s.nextInt();
					s.nextLine();  
				}else if(type == 2) {
					System.out.println("�����ϰ��� �ϴ� ������ �̸��� �Է��ϼ���.");
					name = s.nextLine();
				}
		
					System.out.println("����ϴ� ������ �Է��ϼ���");
					hakjum = s.nextInt();
					s.nextLine();
					System.out.println("����ϴ� �ּ�1�� �Է��ϼ���.");
					addr1 =s.nextLine();
					System.out.println("����ϴ� �ּ�2�� �Է��ϼ���");
					addr2 = s.nextLine();
					System.out.println("����ϴ� �̸����� �Է��ϼ���");
					email = s.nextLine();
					
				if(type == 1) {
					String sql = "update test set hakjum = ? , addr1 = ? , addr2 = ? , email = ? where hakbum =?";
					ps = con.prepareStatement(sql);
					ps.setInt(1,hakjum);
					ps.setString(2, addr1);
					ps.setString(3, addr2);
					ps.setString(4, email);
					ps.setInt(5, hakbum);
					ps.executeUpdate();
					System.out.println("�й� "+ hakbum  + "�� �� ������ �����Ͽ����ϴ�.");
				}else if(type ==2) {
				
					String sql = "update test set hakjum =?, addr1 = ? ,addr2 = ? , email = ? where name = ?";
					ps = con.prepareStatement(sql);
					ps.setInt(1, hakjum);
					ps.setString(2, addr1);
					ps.setString(3,addr2);
					ps.setString(4, email);
					ps.setString(5, name);
					ps.executeUpdate();
					System.out.println(name + "�� ������ �����Ͽ����ϴ�.");
				}
					System.out.println("update����");
		}catch(Exception e) {
			System.out.println("update ����");
		}
	
	}
	
}
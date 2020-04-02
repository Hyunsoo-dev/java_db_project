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
	
	public void getCon() { //DB에 접속하는 메소드
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
			System.out.println("등록하고자 하는 학번을 입력하세요.");
			hakbum = s.nextInt();
			s.nextLine();
			System.out.println("이름을 입력하세요");
			name = s.nextLine();
			System.out.println("학점을 입력하세요");
			hakjum = s.nextInt();
			s.nextLine();
			System.out.println("주소1을 입력하세요");
			addr1 = s.nextLine();
			System.out.println("주소2를 입력하세요");
			addr2 = s.nextLine();
			System.out.println("이메일을 입력하세요.");
			email = s.nextLine();
		
			String sql = "insert into test values (?,?,?,?,?,?)";
					//testMember 의 컬럼(필드)의 갯수만큼 ? 기호를 나열해줌.
			ps = con.prepareStatement(sql);
			ps.setInt(1, hakbum);
			ps.setString(2, name);
			ps.setInt(3, hakjum);
			ps.setString(4,addr1);
			ps.setString(5, addr2);
			ps.setString(6, email);
			ps.executeUpdate();
			System.out.println("insert 성공");
				
		}catch(Exception e) {
				System.out.print("insert 실패");
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
			System.out.println("조회하고자 하는 정보의 이름을 입력하세요");
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
			System.out.println("select 성공");
		}catch(Exception e) {
			System.out.println("select 실패");
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
			System.out.println("정보 검색 기준 :학번은 1 입력, 이름은 2 입력--둘 중 한가지를 선택하세요.");
			type = s.nextInt();
			s.nextLine();
			if(type == 1) {
				System.out.println("삭제하고자 하는 정보의 학번을 입력하세요.");
				hakbum = s.nextInt();
				s.nextLine();  
			}else if(type == 2) {
				System.out.println("삭제하고자 하는 정보의 이름을 입력하세요.");
				name = s.nextLine();
			}
			
			
			if(type == 1) {
				String sql = "delete from test where hakbum =?";
				ps = con.prepareStatement(sql);
			
				ps.setInt(1, hakbum);
				ps.executeUpdate();
				System.out.println("학번 "+ hakbum  + "번 의 정보를 삭제하였습니다.");
			}else if(type ==2) {
			
				String sql = "delete from test where name =?";
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.executeUpdate();
				System.out.println(name + "의 정보를 삭제하였습니다.");
			}
				System.out.println("delete성공");
			
		}catch(Exception e) {
			System.out.println("delete 오류");
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
				System.out.println("정보 검색 기준 :학번은 1 입력, 이름은 2 입력--둘 중 한가지를 선택하세요.");
				type = s.nextInt();
				s.nextLine();
				if(type == 1) {
					System.out.println("수정하고자 하는 정보의 학번을 입력하세요.");
					hakbum = s.nextInt();
					s.nextLine();  
				}else if(type == 2) {
					System.out.println("수정하고자 하는 정보의 이름을 입력하세요.");
					name = s.nextLine();
				}
		
					System.out.println("희망하는 학점을 입력하세요");
					hakjum = s.nextInt();
					s.nextLine();
					System.out.println("희망하는 주소1을 입력하세요.");
					addr1 =s.nextLine();
					System.out.println("희망하는 주소2를 입력하세요");
					addr2 = s.nextLine();
					System.out.println("희망하는 이메일을 입력하세요");
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
					System.out.println("학번 "+ hakbum  + "번 의 정보를 수정하였습니다.");
				}else if(type ==2) {
				
					String sql = "update test set hakjum =?, addr1 = ? ,addr2 = ? , email = ? where name = ?";
					ps = con.prepareStatement(sql);
					ps.setInt(1, hakjum);
					ps.setString(2, addr1);
					ps.setString(3,addr2);
					ps.setString(4, email);
					ps.setString(5, name);
					ps.executeUpdate();
					System.out.println(name + "의 정보를 수정하였습니다.");
				}
					System.out.println("update성공");
		}catch(Exception e) {
			System.out.println("update 오류");
		}
	
	}
	
}
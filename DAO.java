package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DAO {
	
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String id = "system";
		String pass = "oracle";
		
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		Scanner s = new Scanner(System.in);
				
		
		
		
	
		public void getCon() {//DB 접속 메소드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //oralce driver 등록 
			con = DriverManager.getConnection(url, id, pass); //오라클에 접속
			System.out.println("접속 성공");
		}catch(Exception e) {
			System.out.println("접속 오류");
		}
		
	}	
		
		
		public void selector() {//조회 메소드 
			
			getCon();
			try {
				String order_no;
				String customer;
				String order_date;
				int order_price;
				System.out.println("주문번호를 입력하세요");
				order_no = s.nextLine();
				
				String sql = "select * from jumun3 where order_no = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1,order_no);
				rs = ps.executeQuery();
			
				while(rs.next()) {
				
				order_no = rs.getString(1);
				customer = rs.getString(2);
				order_date = rs.getString(3);
				order_price = rs.getInt(4);
				
				System.out.println(order_no + "\t" + customer + "\t" + order_date + "\t" + order_price);
				}
			
					System.out.println("조회 성공");
				}catch(Exception e) {
					System.out.println("조회 실패");
				}
			}
		
		
	
	
	
	}

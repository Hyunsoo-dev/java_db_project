package memberDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import memberDto.MemberDTO;

public class MemberDAO {
	private Connection con;			 
	private PreparedStatement ps;	
	private ResultSet rs;		
	
	private String url, user, pass;

	
	
	public MemberDAO() { //MemberDAO 생성하면 DB와 연결됨.
		url ="jdbc:oracle:thin:@127.0.0.1:1521:xe";
		user = "system";
		pass = "oracle";
		
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass); 
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//전체 회원 보기
	public void select() {
		
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		int id;
		String name;
		String pass;
		String tel;
		String email;
		String jumin1;
		String jumin2;
		String addr1;
		String addr2;
		
		try {
			String sql = "select * from member4";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				id = rs.getInt(1);
				name = rs.getString(2);
				pass = rs.getString(3);
				tel = rs.getString(4);
				email = rs.getString(5);
				jumin1 = rs.getString(6);
				jumin2 = rs.getString(7);
				addr1 = rs.getString(8);
				addr2 = rs.getString(9);
				
				MemberDTO dto = new MemberDTO(id,name,pass,tel,email,jumin1,jumin2,addr1,addr2);
				list.add(dto);
			
				
				//System.out.println(id + "\t"  + name + "\t"  + tel + "\t"  + email + "\t"  + jumin1 +
				//						"\t"  + jumin2 + "\t"  + addr1 + "\t"  + addr2);
			}
			for(int i = 0; i < list.size(); i++) {
				MemberDTO dto= list.get(i);
				id = dto.getId();
				name = dto.getName();
				pass = dto.getPass();
				tel = dto.getTel();
				email = dto.getEmail();
				jumin1 = dto.getJumin1();
				jumin2 = dto.getJumin2();
				addr1 = dto.getAddr1();
				addr2 = dto.getAddr2();
				System.out.println(id + "\t"  + name + "\t" + pass + "\t" +  tel + "\t"  + email + "\t"  + jumin1 +
						"\t"  + jumin2 + "\t"  + addr1 + "\t"  + addr2);
			};
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	//회원입력
	public void insert() {
		
		int id;
		String name;
		String pass;
		String tel;
		String email;
		String jumin1;
		String jumin2;
		String addr1;
		String addr2;
		
		Scanner s= new Scanner(System.in);
		MemberDTO dto = new MemberDTO();
		try {
			
			while(true) {
				id = search(); //아이디 중복 체크 메소드 search() 호출
				String sql = "select count(*) from member4 where id = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				int count = 0;
				while(rs.next()) {
					count = rs.getInt(1);
				}
				if(count > 0) {
					System.out.println("중복된 아이디가 있습니다.");
					continue;
				}else 
					break;
			} //while 끝
			
			System.out.println("이름을 입력하세요.");
			name = s.nextLine();
			System.out.println("비밀번호를 입력하세요.");
			pass = s.nextLine();
			System.out.println("전화번호를 입력하세요.");
			tel = s.nextLine();
			System.out.println("이메일을 입력하세요.");
			email = s.nextLine();
			System.out.println("주민번호 앞자리를 입력하세요.");
			jumin1 = s.nextLine();
			
			
			while(true) {
			System.out.println("주민번호 뒷자리를 입력하세요.");
			jumin2 = s.nextLine();
			if(dto.setJumin2(jumin2) != null) {
				jumin2 = dto.setJumin2(jumin2);
				break;
			}else { 
				System.out.println("실패");
				continue;
			}
				
			}
			System.out.println("주소1을 입력하세요.");
			addr1 =  s.nextLine();
			System.out.println("주소2를 입력하세요.");
			addr2 = s.nextLine();
			
			dto = new MemberDTO(id,name,pass,tel,email,jumin1,jumin2,addr1,addr2);
			String sql = "insert into member4 values (?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, dto.getId());
			ps.setString(2,dto.getName());
			ps.setString(3,dto.getPass());
			ps.setString(4,dto.getTel());
			ps.setString(5,dto.getEmail());
			ps.setString(6,dto.getJumin1());
			ps.setString(7,dto.getJumin2());
			ps.setString(8,dto.getAddr1());
			ps.setString(9,dto.getAddr2());
			
			ps.executeUpdate();
			System.out.println(dto.getId() + "\t" + dto.getName() + "\t" + dto.getPass() + "\t" + dto.getTel() + "\t" + dto.getEmail() + "\t" + dto.getJumin1() + "\t" + dto.getJumin2() 
								      + "\t" + dto.getAddr1() + "\t" + dto.getAddr2() );
			System.out.println("입력되었습니다.");
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	//번호로 회원 검색 
		public int search() {
			
			String name;
			String pass;
			String tel;
			String email;
			String jumin1;
			String jumin2;
			String addr1;
			String addr2;
			int id = 0;
			
			try {
				
				while(true) {
				Scanner s = new Scanner(System.in);
				System.out.println("입력할 id를 입력하세요.");
				id = s.nextInt();
				s.nextLine();
				String sql = "select count(*) from member4 where id =?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
					
				rs = ps.executeQuery();
				int count = 0;
				while(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count > 0) {
					System.out.println("중복된 아이디가 있습니다. 새로운 아이디를 입력하세요.");
					continue;
				}else 
					break;
			}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			return id;
		}
	//회원조회
	public void view() {
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		int no;
		int id;
		String name;
		String pass;
		String tel;
		String email;
		String jumin1;
		String jumin2;
		String addr1;
		String addr2;
		
		System.out.println("회원 번호 입력 : 1 , 회원 이름 입력 : 2");
		Scanner s = new Scanner(System.in);
		no = s.nextInt();
		s.nextLine();
		if(no == 1) {//번호 입력
			try {
				
				System.out.println("조회 할 아이디를 입력하세요");
				id = s.nextInt();
				s.nextLine();
				String sql = "select * from member4 where id = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				int count = ps.executeUpdate();
								
				if(count > 0) {
					rs = ps.executeQuery();
					
					while(rs.next()) {
					id = rs.getInt(1);	
					name = rs.getString(2);
					pass = rs.getString(3);
					tel = rs.getString(4);
					email = rs.getString(5);
					jumin1 = rs.getString(6);
					jumin2 = rs.getString(7);
					addr1 = rs.getString(8);
					addr2 = rs.getString(9);
					
					MemberDTO dto = new MemberDTO(id,name,pass,tel,email,jumin1,jumin2,addr1,addr2);
					list.add(dto);
					
			
					
					//System.out.println(id + "\t"  + name + "\t" + pass + "\t"  + tel + "\t"  + email + "\t"  + jumin1 +
					//		"\t"  + jumin2 + "\t"  + addr1 + "\t"  + addr2);
					}
					for(int i = 0; i < list.size(); i++) {
						MemberDTO dto= list.get(i);
						id = dto.getId();
						name = dto.getName();
						pass = dto.getPass();
						tel = dto.getTel();
						email = dto.getEmail();
						jumin1 = dto.getJumin1();
						jumin2 = dto.getJumin2();
						addr1 = dto.getAddr1();
						addr2 = dto.getAddr2();
						
						System.out.println(id + "\t"  + name + "\t" + pass + "\t" +  tel + "\t"  + email + "\t"  + jumin1 +
											"\t"  + jumin2 + "\t"  + addr1 + "\t"  + addr2);
					}
					
				}else {
				System.out.println("조회결과가 없습니다.");
				}
			
			}
					
			 catch (Exception e) {
				e.printStackTrace();
			} 
			
		}else if (no == 2) {//이름 입력
			try {
				System.out.println("조회 할 이름를 입력하세요");
				name = s.nextLine();
				String sql = "select * from member4 where name = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				int count = ps.executeUpdate();
				if(count > 0) {
					rs = ps.executeQuery();
				while(rs.next()) {
					id = rs.getInt(1);
					name = rs.getString(2);
					pass = rs.getString(3);
					tel = rs.getString(4);
					email = rs.getString(5);
					jumin1 = rs.getString(6);
					jumin2 = rs.getString(7);
					addr1 = rs.getString(8);
					addr2 = rs.getString(9);
					
					MemberDTO dto = new MemberDTO(id,name,pass,tel,email,jumin1,jumin2,addr1,addr2);
					list.add(dto);
					
					
					//System.out.println(id + "\t"  + name + "\t"  + tel + "\t"  + email + "\t"  + jumin1 +
					//		"\t"  + jumin2 + "\t"  + addr1 + "\t"  + addr2);
				}
				
				for(int i = 0; i < list.size(); i++) {
					MemberDTO dto= list.get(i);
					id = dto.getId();
					name = dto.getName();
					pass = dto.getPass();
					tel = dto.getTel();
					email = dto.getEmail();
					jumin1 = dto.getJumin1();
					jumin2 = dto.getJumin2();
					addr1 = dto.getAddr1();
					addr2 = dto.getAddr2();
					
					System.out.println(id + "\t"  + name + "\t" + pass + "\t" +  tel + "\t"  + email + "\t"  + jumin1 +
										"\t"  + jumin2 + "\t"  + addr1 + "\t"  + addr2);
				}
				
			}else 
				System.out.println("조회 결과가 없습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else {//그 이외의 수 입력 
			System.out.println();
			System.out.println("잘못 입력했습니다. 1과 2 중 하나를 입력하세요.");
			view();
			
		}
			
		
	}
	//회원수정
	public void update() {
		int no;
		int id;
		String name;
		String pass;
		String tel;
		String email;
		String jumin1;
		String jumin2;
		String addr1;
		String addr2;
		
		try {
			
			Scanner s = new Scanner(System.in);
			System.out.println("수정할 회원의 번호를 입력하세요.");
			no = s.nextInt();
			s.nextLine();
			String sql = "select * from member4 where id =?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			int count = ps.executeUpdate();
			
			if(count > 0) {
				rs = ps.executeQuery();
				while(rs.next()) {
					id = rs.getInt(1);
					name = rs.getString(2);
					pass = rs.getString(3);
					tel = rs.getString(4);
					email = rs.getString(5);
					jumin1 = rs.getString(6);
					jumin2 = rs.getString(7);
					addr1 = rs.getString(8);
					addr2 = rs.getString(9);
					
					MemberDTO dto = new MemberDTO(no,name,pass,tel,email,jumin1,jumin2,addr1,addr2);
					
					System.out.println(dto.getId() + "\t"  + dto.getName() + "\t"  + dto.getTel() + "\t"  + dto.getEmail() + "\t"  + dto.getJumin1() +
							"\t"  + dto.getJumin2() + "\t"  + dto.getAddr1() + "\t"  + dto.getAddr2());
				}
			}else 
				System.out.println("수정할 수 없습니다.");
			
			
			System.out.println("회원의 이름을 수정하세요.");
			name = s.nextLine();
			System.out.println("회원의 비밀번호를 수정하세요.");
			pass = s.nextLine();
			System.out.println("회원의 전화번호를 수정하세요.");
			tel = s.nextLine();
			System.out.println("회원의 이메일을 수정하세요.");
			email = s.nextLine();
			System.out.println("회원의 주민번호 앞자리를 수정하세요.");
			jumin1 = s.nextLine();
			System.out.println("회원의 주민번호 뒷자리를 수정하세요.");
			jumin2 = s.nextLine();
			System.out.println("회원의 주소1을 수정하세요.");
			addr1 = s.nextLine();
			System.out.println("회원의 주소2를 수정하세요.");
			addr2 = s.nextLine();
			
			MemberDTO dto = new MemberDTO(no,name,pass,tel,email,jumin1,jumin2,addr1,addr2);
			
			sql = "update member4 set name = ?, pass = ? , tel = ?, email = ? , jumin1 = ?, jumin2 = ?, addr1 = ?, addr2 = ? where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getPass());
			ps.setString(3, dto.getTel());
			ps.setString(4, dto.getEmail());
			ps.setString(5, dto.getJumin1());
			ps.setString(6, dto.getJumin2());
			ps.setString(7, dto.getAddr1());
			ps.setString(8, dto.getAddr2());
			ps.setInt(9, dto.getId());
			
			ps.executeUpdate();
			System.out.println("id : " +  dto.getId() + "\t" + "name :" + dto.getName() + "\t" + "pass : " + dto.getPass() + "\t" + "tel : " + dto.getTel() +  "\t" +"email : " + dto.getEmail()
					+ "\t" + "jumin1 :" + dto.getJumin1() + "\t" + "jumin2 :" + dto.getJumin2() + "\t" + "addr1 :" + dto.getAddr1() + "\t" + "addr2 : " + dto.getAddr2()  +" 로 수정완료");
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
	}
	//회원삭제
	public void delete() {
		int no;
		int id;
		String name;
		String pass;
		String tel;
		String email;
		String jumin1;
		String jumin2;
		String addr1;
		String addr2;
		
		try {
			MemberDTO dto = null;
			Scanner s = new Scanner(System.in);
		  System.out.println("삭제할 회원의 회원번호를 입력하세요.");
		  no = s.nextInt();
		  String sql = "select * from member4 where id = ?";
		  ps = con.prepareStatement(sql);
		  ps.setInt(1, no);
		  int count = ps.executeUpdate();
		  if(count > 0) {
			  rs = ps.executeQuery();
			  while(rs.next()) {
				  id = rs.getInt(1);
				  name = rs.getString(2);
				  pass = rs.getString(3);
				  tel = rs.getString(4);
				  email = rs.getString(5);
				  jumin1 = rs.getString(6);
				  jumin2 = rs.getString(7);
				  addr1 = rs.getString(8);
				  addr2 = rs.getString(9);
				  
				dto = new MemberDTO(no,name,pass,tel,email,jumin1,jumin2,addr1,addr2);
				System.out.println("조회결과 ---> id : " +  dto.getId() + "\t" + "name :" + dto.getName() + "\t" + "pass : " + dto.getPass() + "\t" + "tel : " + dto.getTel() +  "\t" +"email : " + dto.getEmail()
				+ "\t" + "jumin1 :" + dto.getJumin1() + "\t" + "jumin2 :" + dto.getJumin2() + "\t" + "addr1 :" + dto.getAddr1() + "\t" + "addr2 : " + dto.getAddr2());
				  
			  }
			  sql = "delete from member4 where id = ?";
			  ps = con.prepareStatement(sql);
			  ps.setInt(1, dto.getId());
			  ps.executeUpdate();
			  System.out.println("회원번호 " + dto.getId() + "의 회원정보가 삭제 되었습니다.");
		  }else 
			  System.out.println("삭제할 수 없다.");
		  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
		public void exit() {
			System.out.println("친구찾기 프로그램을 종료합니다.");
			System.exit(0);
		}
	
	
}

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

	
	
	public MemberDAO() { //MemberDAO �����ϸ� DB�� �����.
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
	
	
	//��ü ȸ�� ����
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
	//ȸ���Է�
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
				id = search(); //���̵� �ߺ� üũ �޼ҵ� search() ȣ��
				String sql = "select count(*) from member4 where id = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				int count = 0;
				while(rs.next()) {
					count = rs.getInt(1);
				}
				if(count > 0) {
					System.out.println("�ߺ��� ���̵� �ֽ��ϴ�.");
					continue;
				}else 
					break;
			} //while ��
			
			System.out.println("�̸��� �Է��ϼ���.");
			name = s.nextLine();
			System.out.println("��й�ȣ�� �Է��ϼ���.");
			pass = s.nextLine();
			System.out.println("��ȭ��ȣ�� �Է��ϼ���.");
			tel = s.nextLine();
			System.out.println("�̸����� �Է��ϼ���.");
			email = s.nextLine();
			System.out.println("�ֹι�ȣ ���ڸ��� �Է��ϼ���.");
			jumin1 = s.nextLine();
			
			
			while(true) {
			System.out.println("�ֹι�ȣ ���ڸ��� �Է��ϼ���.");
			jumin2 = s.nextLine();
			if(dto.setJumin2(jumin2) != null) {
				jumin2 = dto.setJumin2(jumin2);
				break;
			}else { 
				System.out.println("����");
				continue;
			}
				
			}
			System.out.println("�ּ�1�� �Է��ϼ���.");
			addr1 =  s.nextLine();
			System.out.println("�ּ�2�� �Է��ϼ���.");
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
			System.out.println("�ԷµǾ����ϴ�.");
					
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
	
	//��ȣ�� ȸ�� �˻� 
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
				System.out.println("�Է��� id�� �Է��ϼ���.");
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
					System.out.println("�ߺ��� ���̵� �ֽ��ϴ�. ���ο� ���̵� �Է��ϼ���.");
					continue;
				}else 
					break;
			}
		
			} catch (Exception e) {
				e.printStackTrace();
			}
			return id;
		}
	//ȸ����ȸ
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
		
		System.out.println("ȸ�� ��ȣ �Է� : 1 , ȸ�� �̸� �Է� : 2");
		Scanner s = new Scanner(System.in);
		no = s.nextInt();
		s.nextLine();
		if(no == 1) {//��ȣ �Է�
			try {
				
				System.out.println("��ȸ �� ���̵� �Է��ϼ���");
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
				System.out.println("��ȸ����� �����ϴ�.");
				}
			
			}
					
			 catch (Exception e) {
				e.printStackTrace();
			} 
			
		}else if (no == 2) {//�̸� �Է�
			try {
				System.out.println("��ȸ �� �̸��� �Է��ϼ���");
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
				System.out.println("��ȸ ����� �����ϴ�.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else {//�� �̿��� �� �Է� 
			System.out.println();
			System.out.println("�߸� �Է��߽��ϴ�. 1�� 2 �� �ϳ��� �Է��ϼ���.");
			view();
			
		}
			
		
	}
	//ȸ������
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
			System.out.println("������ ȸ���� ��ȣ�� �Է��ϼ���.");
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
				System.out.println("������ �� �����ϴ�.");
			
			
			System.out.println("ȸ���� �̸��� �����ϼ���.");
			name = s.nextLine();
			System.out.println("ȸ���� ��й�ȣ�� �����ϼ���.");
			pass = s.nextLine();
			System.out.println("ȸ���� ��ȭ��ȣ�� �����ϼ���.");
			tel = s.nextLine();
			System.out.println("ȸ���� �̸����� �����ϼ���.");
			email = s.nextLine();
			System.out.println("ȸ���� �ֹι�ȣ ���ڸ��� �����ϼ���.");
			jumin1 = s.nextLine();
			System.out.println("ȸ���� �ֹι�ȣ ���ڸ��� �����ϼ���.");
			jumin2 = s.nextLine();
			System.out.println("ȸ���� �ּ�1�� �����ϼ���.");
			addr1 = s.nextLine();
			System.out.println("ȸ���� �ּ�2�� �����ϼ���.");
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
					+ "\t" + "jumin1 :" + dto.getJumin1() + "\t" + "jumin2 :" + dto.getJumin2() + "\t" + "addr1 :" + dto.getAddr1() + "\t" + "addr2 : " + dto.getAddr2()  +" �� �����Ϸ�");
			
			
			
			
			
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
	//ȸ������
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
		  System.out.println("������ ȸ���� ȸ����ȣ�� �Է��ϼ���.");
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
				System.out.println("��ȸ��� ---> id : " +  dto.getId() + "\t" + "name :" + dto.getName() + "\t" + "pass : " + dto.getPass() + "\t" + "tel : " + dto.getTel() +  "\t" +"email : " + dto.getEmail()
				+ "\t" + "jumin1 :" + dto.getJumin1() + "\t" + "jumin2 :" + dto.getJumin2() + "\t" + "addr1 :" + dto.getAddr1() + "\t" + "addr2 : " + dto.getAddr2());
				  
			  }
			  sql = "delete from member4 where id = ?";
			  ps = con.prepareStatement(sql);
			  ps.setInt(1, dto.getId());
			  ps.executeUpdate();
			  System.out.println("ȸ����ȣ " + dto.getId() + "�� ȸ�������� ���� �Ǿ����ϴ�.");
		  }else 
			  System.out.println("������ �� ����.");
		  
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
			System.out.println("ģ��ã�� ���α׷��� �����մϴ�.");
			System.exit(0);
		}
	
	
}

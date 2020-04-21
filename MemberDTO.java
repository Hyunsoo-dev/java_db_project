package memberDto;

import memberDao.MemberDAO;

public class MemberDTO {

	private int id;
	private String name;
	private String pass;
	private String tel;
	private String email;
	private String jumin1;
	private String jumin2;
	private String addr1;
	private String addr2;
	public MemberDTO() {}
	public MemberDTO(int id, String name, String pass, String tel, String email, String jumin1, String jumin2 , String addr1 , String addr2) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.tel = tel;
		this.email = email;
		this.jumin1 = jumin1;
		this.jumin2 = jumin2;
		this.addr1 = addr1;
		this.addr2 = addr2;
		
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}
	
	public String getTel() {
		return tel;
	}
	
	public String getEmail() {
		return email;
	}

	public String getJumin1() {
		return jumin1;
	}
	
	public String setJumin2(String jumin2) {
	char front	= jumin2.charAt(0);
	if(front > '0' && front < '5') {
		this.jumin2 = jumin2;
		return this.jumin2;
	}else {
		System.out.println("다시 입력하세요.");
		this.jumin2 = null;
		return this.jumin2;
	} 
		
		
	}
	public String getJumin2() {
		return jumin2;
	}
	
	public String getAddr1() {
		return addr1;
	}
	
	public String getAddr2() {
		return addr2;
	}
	
	
	
}

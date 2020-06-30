package com.javaex.vo;

public class GuestbookVo {
	//필드
	private int no;
	private String name,pw,content,reg_date;
	
	//생성자
	public GuestbookVo() {
		
	}
	
	public GuestbookVo(int no, String name, String pw, String content, String reg_date) {
		this.no = no;
		this.name = name;
		this.pw = pw;
		this.content = content;
		this.reg_date = reg_date;
	}

	public GuestbookVo(String name, String pw, String content, String reg_date) {
		this.name = name;
		this.pw = pw;
		this.content = content;
		this.reg_date = reg_date;
	}

	public GuestbookVo(String name, String pw, String content) {
		super();
		this.name = name;
		this.pw = pw;
		this.content = content;
	}

	//g.s
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	//toString
	@Override
	public String toString() {
		return "guestbookVo [no=" + no + ", name=" + name + ", pw=" + pw + ", content=" + content + ", reg_date="
				+ reg_date + "]";
	}
	
}

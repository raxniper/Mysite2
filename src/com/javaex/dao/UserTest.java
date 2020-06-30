package com.javaex.dao;

import com.javaex.vo.UserVo;

public class UserTest {

	public static void main(String[] args) {
		
		UserDao dao = new UserDao();
		UserVo vo1 = new UserVo("8282","현수","male","김현수");
		
		
		dao.Update(vo1);
	}

}

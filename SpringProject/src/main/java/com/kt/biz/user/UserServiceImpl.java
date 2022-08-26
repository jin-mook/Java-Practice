package com.kt.biz.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	public void insertUser(UserVO vo) {
	}

	public void updateUser(UserVO vo) {
	}

	public void deleteUser(UserVO vo) {
	}

	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

	public List<UserVO> getUserList(UserVO vo) {
		return null;
	}

}

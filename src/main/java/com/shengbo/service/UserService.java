package com.shengbo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shengbo.mapper.UserMapper;
import com.shengbo.model.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User login(String account, String passWord, String ip) throws Exception{
		User user = userMapper.login(account, passWord);
		userMapper.loginLog(ip, account);
		return user;
	}
	
	public void register(User user) throws Exception{
		userMapper.register(user);
	}
	
	public void loginOutLog(String account) throws Exception{
		userMapper.loginOutLog(account);
	}
	
	public List<User> userList() {
		return userMapper.userList();
	}
}

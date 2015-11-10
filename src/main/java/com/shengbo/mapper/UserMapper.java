package com.shengbo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shengbo.model.User;

public interface UserMapper {

	public void register(@Param("user") User user) throws Exception;
	
	public User login(@Param("account") String account, @Param("passWord") String passWord) throws Exception;
	
	public void loginLog(@Param("ip") String ip,@Param("account") String account) throws Exception;
	
	public void loginOutLog(@Param("account") String account) throws Exception;

	public List<User> userList();
}

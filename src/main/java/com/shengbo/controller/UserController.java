package com.shengbo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shengbo.model.User;
import com.shengbo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(User user, HttpServletRequest req, HttpServletResponse resp) {
		user.setIp(req.getRemoteHost());
		try {
			userService.register(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/success.html";
	}

	@RequestMapping(value ="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> login(@RequestParam("account") String account, @RequestParam("passWord") String passWord
			, HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map  = new HashMap<String,Object>();
		String ip = getRemortIP(request);
		User user = null;
		try {
			user = userService.login(account, passWord, ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(user!=null) {
			user.setIsLogin(2);
			map.put("account", user.getAccount());
		} else {
			response.setStatus(503);
		}
		return map;
	}

	public String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	
	
	@ResponseBody
	@RequestMapping(value="userList", method=RequestMethod.GET, produces = "application/json")
	public Map<String,Object> userList(
			HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("userList", userService.userList());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="loginOut", method=RequestMethod.GET, produces = "application/json")
	public String loginOut(@RequestParam("account") String account,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			userService.loginOutLog(account);
		} catch (Exception e) {
			response.setStatus(503);
		}
		return "";
	}
}

package com.talklife.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.talklife.common.pojo.IdentifyCode;
import com.talklife.common.service.itf.IdentifyCodeService;
import com.talklife.common.utils.IdentifyingCode;
import com.talklife.user.pojo.User;
import com.talklife.user.service.itf.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name = "userServiceImpl")
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Resource(name = "identifyCodeServiceImpl")
	private IdentifyCodeService identifyCodeService;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String loginIndex(HttpServletRequest request, Model model) {
		User userLogin = new User();
		userLogin.setLoginid(request.getParameter("loginid"));
		userLogin.setLoginpwd(request.getParameter("loginpwd"));
		if (userLogin.getLoginid() != null && userLogin.getLoginpwd() != null) {
			boolean b = this.userService.getLoginUser(userLogin);
			if (b)
				model.addAttribute("user", userLogin);
			model.addAttribute("user", null);
		}
		return "/index";
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String register(HttpServletRequest request, Model model) {
		String result = "";
		String phone = request.getParameter("phone");//手机号
		String code = request.getParameter("verification");//验证码
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String date = df.format(new Date());
		/*
		 * 发送手机验证码服务
		 */
		IdentifyCode codeinfo = new IdentifyCode();
		codeinfo.setCode(code);
		codeinfo.setCreatetime(date);
		codeinfo.setPhonenum(phone);
		codeinfo.setType("Register");
		/*
		 * 校验验证码成功，完成注册，失败返回错误信息
		 */
		boolean b = this.identifyCodeService.checkCode(codeinfo);
		if (b) {
			String userid = IdentifyingCode.getRandomString(20);
			User user = new User();
			user.setUserid(userid);
			user.setLoginid(phone);
			this.userService.userRegister(user);
			result = "/index";
		} else {
			result = "/error101";
		}
		return result;
	}

	/**
	 * 完善用户资料
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/perfect.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String perfect(HttpServletRequest request, Model model) {
		User userinfo = new User();
		userinfo.setTruename(request.getParameter("truename"));
		userinfo.setLoginpwd(request.getParameter("loginpwd"));
		userinfo.setAge(Integer.parseInt(request.getParameter("age")));
		userinfo.setAge(Integer.valueOf(request.getParameter("age")).intValue());
		userinfo.setGender(request.getParameter("gender"));
		userinfo.setMail(request.getParameter("mail"));
		userinfo.setQq(request.getParameter("qq"));
		userinfo.setWeibo(request.getParameter("weibo"));
		this.userService.userPerfect(userinfo);
		return "index";
	}

}

package com.talklife.common.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talklife.common.pojo.IdentifyCode;
import com.talklife.common.service.itf.IdentifyCodeService;
import com.talklife.common.utils.IdentifyingCode;

@Controller
@RequestMapping("/code")
public class IdentifyCodeController {

	@Resource(name = "identifyCodeServiceImpl")
	private IdentifyCodeService identifyCodeService;

	public IdentifyCodeService getIdentifyCodeService() {
		return identifyCodeService;
	}

	public void setIdentifyCodeService(IdentifyCodeService identifyCodeService) {
		this.identifyCodeService = identifyCodeService;
	}

	@RequestMapping(value = "/createCode.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String createCode(HttpServletRequest request) throws HttpException,
			IOException {
		// 获取参数
		String phone = request.getParameter("phone");// 获取手机号码
		String type = request.getParameter("type");// 获取模板类型
		String code = request.getParameter("code");// 获取验证码
		// 存入数据库
		IdentifyCode codeinfo = new IdentifyCode();
		codeinfo.setCodeid(IdentifyingCode.getRandomString(20));// ID
		codeinfo.setPhonenum(phone);
		codeinfo.setCode(code);
		codeinfo.setType(type);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		codeinfo.setCreatetime(df.format(new Date()));
		codeinfo.setDr("0");
		this.identifyCodeService.saveCode(codeinfo);
		// 发送短信
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(
				"http://localhost:8080/talklife/communication/smsservice.do");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("code", code),
				new NameValuePair("phone", phone),
				new NameValuePair("type", type) };
		post.setRequestBody(data);
		client.executeMethod(post);
		String result = new String(post.getResponseBodyAsString().getBytes(
				"utf-8"));
		post.releaseConnection();
		return result;
	}

}

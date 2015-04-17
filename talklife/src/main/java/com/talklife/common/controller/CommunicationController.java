package com.talklife.common.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.talklife.common.utils.EmailUtil;
import com.talklife.common.utils.XmlHelper;

@Controller
@RequestMapping("/communication")
public class CommunicationController {
	private Map<String, Map<String, String>> smsData = null;
	private Map<String, Map<String, String>> emailData = null;

	/**
	 * 短信发送服务
	 * 
	 * @param request
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@RequestMapping(value = "/smsservice.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> SmsService(HttpServletRequest request)
			throws HttpException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		String confile = request.getServletContext().getRealPath("/")
				+ "WEB-INF\\classes\\sms-config.xml";
		// 获取参数
		String phone = request.getParameter("phone");// 获取手机号码
		String type = request.getParameter("type");// 获取模板类型
		String code = request.getParameter("code");// 获取验证码
		if (smsData == null) {
			smsData = XmlHelper.GetConData(confile);
		}
		Map<String, String> accountInfo = smsData.get("accountInfo");// 账户信息
		Map<String, String> tempLateInfo = smsData.get(type);// 模板信息
		String smsText = tempLateInfo.get("value").replace("#CODE#", code);
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = {
				new NameValuePair("Uid", accountInfo.get("Uid")),
				new NameValuePair("Key", accountInfo.get("Key")),
				new NameValuePair("smsMob", phone),
				new NameValuePair("smsText", smsText) };
		post.setRequestBody(data);
		client.executeMethod(post);
		String result = new String(post.getResponseBodyAsString().getBytes(
				"utf-8"));
		post.releaseConnection();
		map.put("code", Integer.valueOf(result).intValue());
		return map;
	}

	/**
	 * 邮件发送服务
	 * 
	 * @param request
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@RequestMapping(value = "/emailservice.do", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> EmailService(HttpServletRequest request)
			throws HttpException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		String rpath = request.getServletContext().getRealPath("/");
		String confile = rpath + "WEB-INF\\classes\\email-config.xml";
		// 获取参数
		String mailto = request.getParameter("mailto");// 获取手机号码
		String type = request.getParameter("type");// 获取模板类型
		String code = request.getParameter("code");// 获取验证码
		if (emailData == null)
			emailData = XmlHelper.GetConData(confile);
		Map<String, String> accountInfo = emailData.get("accountInfo");// 账户信息
		Map<String, String> tempLateInfo = emailData.get(type);// 模板信息
		String templatePath = tempLateInfo.get("value");// 模板路径

		EmailUtil themail = new EmailUtil(accountInfo.get("mail.smtp.host"));
		themail.setNeedAuth(Boolean.getBoolean(accountInfo
				.get("mail.smtp.auth")));
		themail.setSubject(tempLateInfo.get("subject"));// 邮件主题
		themail.setBody(readTxtFile(rpath + templatePath).replace("#CODE#",
				code));// 邮件正文
		themail.setTo(mailto);// 收件人地址
		themail.setFrom(accountInfo.get("username"));// 发件人地址
		themail.setNamePass(accountInfo.get("username"), accountInfo.get("Key"));// 发件人地址和密码
		int b = themail.sendout();
		map.put("code", b);
		return map;
	}

	/**
	 * 读取邮件模板文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readTxtFile(String filePath) {
		String result = "";
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), "utf-8");// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					result += lineTxt;
					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return result;
	}
}

package com.talklife.user.pojo;

/**
 * 用户详细信息
 * 
 * @author Administrator
 *
 */
public class User {
	private String userid; // 用户ID
	private String truename; // 真实名称
	private String gender; // 性别
	private int age; // 年龄
	private String mail; // 邮箱
	private String qq; // QQ号吗
	private String weibo; // 微博号
	private String headering; // 用户头像地址
	private String dr; // 删除标志
	private String loginid; // 用户名
	private String loginpwd; // 用户口令

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getHeadering() {
		return headering;
	}

	public void setHeadering(String headering) {
		this.headering = headering;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

}

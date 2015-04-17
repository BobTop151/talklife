package com.talklife.common.pojo;

public class IdentifyCode {
	private String codeid;    // ID
	private String phonenum;  // 手机号
	private String code;      // 验证码
	private String type;      // 类型
	private String createtime;// 时间
	private String dr;        // 状态

	public String getCodeid() {
		return codeid;
	}

	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}

}

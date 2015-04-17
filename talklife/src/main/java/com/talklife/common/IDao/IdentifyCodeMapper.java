package com.talklife.common.IDao;

import com.talklife.common.pojo.IdentifyCode;

public interface IdentifyCodeMapper {

	
	/**
	 * 保存手机验证码
	 * @param codeinfo
	 */
	void saveIdentifyCode(IdentifyCode codeinfo);

	/**
	 * 检验手机验证码
	 * @param codeinfo
	 * @return
	 */
	String checkCode(IdentifyCode codeinfo);

	/**
	 * 判断是否存在验证码
	 * @param codeinfo
	 * @return
	 */
	int countCode(IdentifyCode codeinfo);

	/**
	 * 更新验证码
	 * @param codeinfo
	 */
	void updateIdentifyCode(IdentifyCode codeinfo);
}
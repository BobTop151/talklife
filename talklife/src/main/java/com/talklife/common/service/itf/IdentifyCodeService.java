package com.talklife.common.service.itf;

import com.talklife.common.pojo.IdentifyCode;

public interface IdentifyCodeService {

	void saveCode(IdentifyCode codeinfo);

	boolean checkCode(IdentifyCode codeinfo);

}

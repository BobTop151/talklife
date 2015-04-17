package com.talklife.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talklife.common.IDao.IdentifyCodeMapper;
import com.talklife.common.pojo.IdentifyCode;
import com.talklife.common.service.itf.IdentifyCodeService;

@Service("identifyCodeServiceImpl")
public class IdentifyCodeServiceImpl implements IdentifyCodeService {

	@Autowired
	private IdentifyCodeMapper identifyCodeDao;

	@Override
	public void saveCode(IdentifyCode codeinfo) {
		if (this.identifyCodeDao.countCode(codeinfo) > 0) {
			this.identifyCodeDao.updateIdentifyCode(codeinfo);
		} else {
			this.identifyCodeDao.saveIdentifyCode(codeinfo);
		}
	}

	@Override
	public boolean checkCode(IdentifyCode codeinfo) {
		String stat = this.identifyCodeDao.checkCode(codeinfo);
		if ("0".equalsIgnoreCase(stat)) {
			return true;
		}
		return false;
	}

}

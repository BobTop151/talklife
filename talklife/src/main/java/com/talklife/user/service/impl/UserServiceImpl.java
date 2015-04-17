package com.talklife.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talklife.common.utils.MD5Helper;
import com.talklife.user.IDao.UserMapper;
import com.talklife.user.pojo.User;
import com.talklife.user.service.itf.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;

	public User getUserById(int id) {
		System.out.println(id + this.userDao.selectId(id).getLoginid());

		return this.userDao.selectId(id);
	}

	/**
	 * 验证用户登录
	 */
	public Boolean getLoginUser(User userLogin) {
		if (userLogin.getLoginid().equals("")|| (userLogin.getLoginpwd().equals(""))) {
			return false;
		} else {
			User user = new User();
			if (this.userDao.selectLogin(userLogin.getLoginid()) != null) {
				user = userDao.selectLogin(userLogin.getLoginid());
				if (user.toString().isEmpty()) {
					return false;
				} else {
					if (user.getLoginpwd().equals(MD5Helper.GetMD5Code(userLogin.getLoginpwd()))) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}
	}

	public User user(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public User selectLogin(String username) {
		// TODO Auto-generated method stub
		return this.userDao.selectLogin(username);
	}

	public User selectByPrimaryKey(int i) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(i);
	}

	public User getUserByusername(String username) {
		// TODO Auto-generated method stub
		return this.userDao.selectLogin(username);
	}

	public int inster(User user) {
		// TODO Auto-generated method stub
		int i = this.userDao.insert(user);
		return i;
	}

	/**
	 * 用户注册
	 */
	@Override
	public void userRegister(User user) {
		this.userDao.saveUser(user);
		
	}

	@Override
	public void userPerfect(User userinfo) {
		this.userDao.updateUser(userinfo);
		
	}

}

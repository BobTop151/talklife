package com.talklife.user.IDao;

import com.talklife.user.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectId(int id);

	User selectLogin(String username);

	/**
	 * 用户数据保存
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * 更新用户数据
	 * @param userinfo
	 */
	void updateUser(User userinfo);

}
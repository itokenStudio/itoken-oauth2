package com.draper.itoken.oauth2.service;

import com.draper.itoken.oauth2.domain.TbUser;

public interface TbUserService {

	/**
	 * 相当于通过账号获取 user
	 * @param username
	 * @return
	 */
	TbUser getByUserName(String username);

}
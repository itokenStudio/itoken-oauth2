package com.draper.itoken.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.draper.itoken.oauth2.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.draper.itoken.oauth2.domain.TbUser;
import com.draper.itoken.oauth2.mapper.TbUserMapper;

/**
 * @author draper_hxy
 */
@Service
public class TbUserServiceImpl implements TbUserService {

	@Autowired
	private TbUserMapper tbUserMapper;

	@Override
	public TbUser getByUserName(String username) {
		QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(TbUser::getUsername, username);
		return tbUserMapper.selectOne(queryWrapper);
	}

}

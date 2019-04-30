package com.draper.itoken.oauth2.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.draper.itoken.oauth2.domain.TbUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author draper_hxy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TbUserMapperTest {

	@Autowired
	private TbUserMapper tbUserMapper;

	@Test
	public void testSelectOne() {
		TbUser tbUser = tbUserMapper
				.selectOne(new QueryWrapper<TbUser>().eq("username", "admin"));
		Assert.assertEquals("draper.hxy@gmail.com", tbUser.getEmail());
	}

}
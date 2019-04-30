package com.draper.itoken.oauth2.config;

import com.draper.itoken.oauth2.domain.TbPermission;
import com.draper.itoken.oauth2.domain.TbUser;
import com.draper.itoken.oauth2.service.TbPermissionService;
import com.draper.itoken.oauth2.service.TbUserService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主要是查询账号，赋予权限。 实现了 UserDetailService 方法
 *
 * @author draper_hxy
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private TbUserService tbUserService;

	@Autowired
	private TbPermissionService tbPermissionService;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		TbUser tbUser = tbUserService.getByUserName(s);

		List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();

		if (tbUser != null) {
			List<TbPermission> tbPermissions = tbPermissionService
					.selectByUserId(tbUser.getId());
			tbPermissions.forEach(tbPermission -> {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
						tbPermission.getEnname());
				grantedAuthorityList.add(grantedAuthority);
			});
			return new User(tbUser.getUsername(), tbUser.getPassword(),
					grantedAuthorityList);
		}
		throw new UsernameNotFoundException("用户不存在");
	}

}

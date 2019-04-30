package com.draper.itoken.oauth2.service;

import com.draper.itoken.oauth2.domain.TbPermission;

import java.util.List;

public interface TbPermissionService {

	List<TbPermission> selectByUserId(Long userId);

}

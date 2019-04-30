package com.draper.itoken.oauth2.service.impl;

import com.draper.itoken.oauth2.domain.TbPermission;
import com.draper.itoken.oauth2.mapper.TbPermissionMapper;
import com.draper.itoken.oauth2.service.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author draper_hxy
 */
@Service
public class TbPermissionServiceImpl implements TbPermissionService {

    @Autowired
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }

}


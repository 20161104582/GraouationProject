package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ViewUserInfo;
import com.web.sys.service.ViewUserInfoService;
import com.web.sys.dao.ViewUserInfoDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ViewUserInfoServiceImpl implements ViewUserInfoService {

	@Resource
	private ViewUserInfoDao viewUserInfoDao;


    @Override
    public ViewUserInfoDao initDao() {
        return viewUserInfoDao;
    }
}

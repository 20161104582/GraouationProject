package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ViewSchoolInfo;
import com.web.sys.service.ViewSchoolInfoService;
import com.web.sys.dao.ViewSchoolInfoDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ViewSchoolInfoServiceImpl implements ViewSchoolInfoService {

	@Resource
	private ViewSchoolInfoDao viewSchoolInfoDao;


    @Override
    public ViewSchoolInfoDao initDao() {
        return viewSchoolInfoDao;
    }
}

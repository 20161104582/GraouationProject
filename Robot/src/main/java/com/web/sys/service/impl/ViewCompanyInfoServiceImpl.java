package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ViewCompanyInfo;
import com.web.sys.service.ViewCompanyInfoService;
import com.web.sys.dao.ViewCompanyInfoDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ViewCompanyInfoServiceImpl implements ViewCompanyInfoService {

	@Resource
	private ViewCompanyInfoDao viewCompanyInfoDao;


    @Override
    public ViewCompanyInfoDao initDao() {
        return viewCompanyInfoDao;
    }
}

package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ProUserCompany;
import com.web.sys.service.ProUserCompanyService;
import com.web.sys.dao.ProUserCompanyDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProUserCompanyServiceImpl implements ProUserCompanyService {

	@Resource
	private ProUserCompanyDao proUserCompanyDao;


    @Override
    public ProUserCompanyDao initDao() {
        return proUserCompanyDao;
    }
}

package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ProCompany;
import com.web.sys.service.ProCompanyService;
import com.web.sys.dao.ProCompanyDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProCompanyServiceImpl implements ProCompanyService {

	@Resource
	private ProCompanyDao proCompanyDao;


    @Override
    public ProCompanyDao initDao() {
        return proCompanyDao;
    }
}

package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ProUserSchool;
import com.web.sys.service.ProUserSchoolService;
import com.web.sys.dao.ProUserSchoolDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProUserSchoolServiceImpl implements ProUserSchoolService {

	@Resource
	private ProUserSchoolDao proUserSchoolDao;


    @Override
    public ProUserSchoolDao initDao() {
        return proUserSchoolDao;
    }
}

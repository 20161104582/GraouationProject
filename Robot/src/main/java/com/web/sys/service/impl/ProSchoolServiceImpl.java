package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ProSchool;
import com.web.sys.service.ProSchoolService;
import com.web.sys.dao.ProSchoolDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProSchoolServiceImpl implements ProSchoolService {

	@Resource
	private ProSchoolDao proSchoolDao;


    @Override
    public ProSchoolDao initDao() {
        return proSchoolDao;
    }
}

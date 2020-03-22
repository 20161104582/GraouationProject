package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ProSignStudent;
import com.web.sys.service.ProSignStudentService;
import com.web.sys.dao.ProSignStudentDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProSignStudentServiceImpl implements ProSignStudentService {

	@Resource
	private ProSignStudentDao proSignStudentDao;


    @Override
    public ProSignStudentDao initDao() {
        return proSignStudentDao;
    }
}

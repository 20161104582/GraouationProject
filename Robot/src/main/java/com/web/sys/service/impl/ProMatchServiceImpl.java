package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ProMatch;
import com.web.sys.service.ProMatchService;
import com.web.sys.dao.ProMatchDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProMatchServiceImpl implements ProMatchService {

	@Resource
	private ProMatchDao proMatchDao;


    @Override
    public ProMatchDao initDao() {
        return proMatchDao;
    }
}

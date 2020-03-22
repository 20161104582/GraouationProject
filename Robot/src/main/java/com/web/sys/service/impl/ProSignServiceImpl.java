package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ProSign;
import com.web.sys.service.ProSignService;
import com.web.sys.dao.ProSignDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProSignServiceImpl implements ProSignService {

	@Resource
	private ProSignDao proSignDao;


    @Override
    public ProSignDao initDao() {
        return proSignDao;
    }
}

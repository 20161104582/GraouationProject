package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ProNotice;
import com.web.sys.service.ProNoticeService;
import com.web.sys.dao.ProNoticeDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProNoticeServiceImpl implements ProNoticeService {

	@Resource
	private ProNoticeDao proNoticeDao;


    @Override
    public ProNoticeDao initDao() {
        return proNoticeDao;
    }
}

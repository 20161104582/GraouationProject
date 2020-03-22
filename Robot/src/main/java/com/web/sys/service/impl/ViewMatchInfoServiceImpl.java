package com.web.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ViewMatchInfo;
import com.web.sys.service.ViewMatchInfoService;
import com.web.sys.dao.ViewMatchInfoDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ViewMatchInfoServiceImpl implements ViewMatchInfoService {

	@Resource
	private ViewMatchInfoDao viewMatchInfoDao;


    @Override
    public ViewMatchInfoDao initDao() {
        return viewMatchInfoDao;
    }
}

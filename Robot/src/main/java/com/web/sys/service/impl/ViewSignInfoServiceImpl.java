package com.web.sys.service.impl;

import javax.annotation.Resource;

import com.web.sys.bean.SysUser;
import org.springframework.stereotype.Service;
import java.util.List;
import com.web.common.utils.PagingUtils;
import com.web.sys.bean.ViewSignInfo;
import com.web.sys.service.ViewSignInfoService;
import com.web.sys.dao.ViewSignInfoDao;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ViewSignInfoServiceImpl implements ViewSignInfoService {

	@Resource
	private ViewSignInfoDao viewSignInfoDao;


    @Override
    public ViewSignInfoDao initDao() {
        return viewSignInfoDao;
    }

    @Override
    public List<ViewSignInfo> queryStudents(SysUser user) {
        return viewSignInfoDao.queryStudents(user);
    }
}

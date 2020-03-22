package com.web.sys.service;

import com.web.sys.bean.SysUser;
import com.web.sys.bean.ViewSignInfo;
import com.web.sys.dao.ViewSignInfoDao;
import com.web.common.service.BaseService;
import java.util.List;
public interface ViewSignInfoService extends  BaseService<ViewSignInfo, ViewSignInfoDao> {


    List<ViewSignInfo> queryStudents(SysUser user);
}
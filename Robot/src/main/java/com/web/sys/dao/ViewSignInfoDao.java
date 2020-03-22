package com.web.sys.dao;

import com.web.common.dao.BaseDao;
import com.web.sys.bean.SysUser;
import com.web.sys.bean.ViewSignInfo;

import java.util.List;

public interface ViewSignInfoDao extends BaseDao<ViewSignInfo> {
    List<ViewSignInfo> queryStudents(SysUser user);
}
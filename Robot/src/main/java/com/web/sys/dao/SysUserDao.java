package com.web.sys.dao;

import com.web.sys.bean.SysUser;
import com.web.common.dao.BaseDao;

import java.util.List;

public interface SysUserDao extends BaseDao<SysUser> {
    List<SysUser> queryNullCharger(SysUser user);
    List<SysUser> queryNullTeacher(SysUser user);
}
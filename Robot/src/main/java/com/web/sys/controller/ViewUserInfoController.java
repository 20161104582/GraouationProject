package com.web.sys.controller;

import javax.annotation.Resource;

import com.web.common.utils.ExcelUtils;
import com.web.sys.bean.*;
import com.web.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.common.bean.DataRes;

import java.util.Date;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ViewUserInfoController {
	
	@Resource
	private ViewUserInfoService viewUserInfoService;

	@Autowired
	SysRoleService roleService;

	@Autowired
	SysUserRoleService userRoleService;

	@Autowired
	ProSchoolService schoolService;

	@Autowired
	ProCompanyService companyService;

	@Autowired
	ProUserCompanyService userCompanyService;

	@Autowired
	ProUserSchoolService userSchoolService;

	@Autowired
	SysUserService userService;
	/**
	 * 删除
	 * @param viewUserInfo
	 * @return
	 */
	@RequestMapping("viewUserInfo/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ViewUserInfo viewUserInfo, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(viewUserInfoService.deleteByPrimaryKey(viewUserInfo));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param viewUserInfo
	 * @return
	 */
	@RequestMapping("viewUserInfo/save")
	@ResponseBody
	public DataRes save(ViewUserInfo viewUserInfo, HttpServletRequest request, HttpServletResponse response){
		if(viewUserInfo.getId()==null || "".equals(viewUserInfo.getId())){
			SysUser user = viewUserInfo.createUser();
			if(viewUserInfo.getRoleId() == null){
				return DataRes.error("-1","角色不能为空");
			}

			// 角色处理
			SysRole r = new SysRole();
			r.setId(viewUserInfo.getRoleId());
			List<SysRole> rs = roleService.queryByCondition(r);
			if(rs == null || rs.size() < 1){
				return DataRes.error("-1","角色不存在");
			}
			SysRole role = rs.get(0);
			user.setCreatetime(new Date());
			user.setPassword("123");

			userService.insert(user);

			SysUserRole sur = new SysUserRole();
			sur.setRoleId(role.getId());
			sur.setUserId(user.getId());
			userRoleService.insert(sur);

			if(viewUserInfo.getCompanyId() != null && !"".equals(viewUserInfo.getCompanyId())){
				ProCompany com = new ProCompany();
				com.setId(viewUserInfo.getCompanyId());
				ProCompany company = companyService.selectByPrimaryKey(com);
				if(company != null){
					ProUserCompany uc = new ProUserCompany();
					uc.setUserId(user.getId());
					uc.setCompanyId(company.getId());
					userCompanyService.insert(uc);
				}
			}

			if(viewUserInfo.getSchoolId() != null && !"".equals(viewUserInfo.getSchoolId())){
				ProSchool sc = new ProSchool();
				sc.setId(viewUserInfo.getSchoolId());
				ProSchool school = schoolService.selectByPrimaryKey(sc);
				if(school != null){
					ProUserSchool us = new ProUserSchool();
					us.setSchoolId(school.getId());
					us.setUserId(user.getId());
					userSchoolService.insert(us);
				}
			}
			return DataRes.success(viewUserInfo);

		}
		SysUser user = viewUserInfo.createUser();
		userService.update(user);

		SysUserRole surUp = new SysUserRole();
		surUp.setUserId(user.getId());
		surUp = userRoleService.queryByCondition(surUp).get(0);
		surUp.setRoleId(viewUserInfo.getRoleId());
		userRoleService.update(surUp);

		ProUserSchool usup = new ProUserSchool();
		usup.setUserId(user.getId());
		List<ProUserSchool> uss = userSchoolService.queryByCondition(usup);
		if(uss != null && uss.size() > 0){
			usup = uss.get(0);
			usup.setSchoolId(viewUserInfo.getSchoolId());
			userSchoolService.update(usup);
		}

		ProUserCompany ucup = new ProUserCompany();
		ucup.setUserId(user.getId());
		List<ProUserCompany> ucc = userCompanyService.queryByCondition(ucup);

		if(ucc != null &&ucc.size() > 0){
			ucup = ucc.get(0);
			ucup.setCompanyId(viewUserInfo.getCompanyId());
			userCompanyService.update(ucup);
		}
		return DataRes.success(viewUserInfo);
	}

    /**
     * 根据主键查询
     * @param viewUserInfo
     * @return
     */
	@RequestMapping("viewUserInfo/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ViewUserInfo viewUserInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewUserInfoService.selectByPrimaryKey(viewUserInfo));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("viewUserInfo/queryViewUserInfoByCondition")
	@ResponseBody
	public DataRes queryByCondition(ViewUserInfo viewUserInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewUserInfoService.queryByCondition(viewUserInfo));
    }

   /**
	* 分页查询
	* @param viewUserInfo 参数
	* @return
	*/
   @RequestMapping("viewUserInfo/selectAll")
   @ResponseBody
   public DataRes selectAll(ViewUserInfo viewUserInfo,HttpServletRequest request, HttpServletResponse response){
	   return DataRes.success(viewUserInfoService.selectAllByPaging(viewUserInfo));
   }
	@RequestMapping("viewUserInfo/selectAllStu")
	@ResponseBody
	public DataRes selectAllStu(ViewUserInfo viewUserInfo,HttpServletRequest request, HttpServletResponse response){
		viewUserInfo.setRoleNum(4L);
		return DataRes.success(viewUserInfoService.selectAllByPaging(viewUserInfo));
	}
	@RequestMapping("viewUserInfo/selectAllTea")
	@ResponseBody
	public DataRes selectAllTea(ViewUserInfo viewUserInfo,HttpServletRequest request, HttpServletResponse response){
		viewUserInfo.setRoleNum(3L);
		return DataRes.success(viewUserInfoService.selectAllByPaging(viewUserInfo));
	}
	@RequestMapping("viewUserInfo/selectAllCom")
	@ResponseBody
	public DataRes selectAllCom(ViewUserInfo viewUserInfo,HttpServletRequest request, HttpServletResponse response){
   		viewUserInfo.setRoleNum(2L);
		return DataRes.success(viewUserInfoService.selectAllByPaging(viewUserInfo));
	}

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("viewUserInfo/export")
	public void export(ViewUserInfo viewUserInfo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ViewUserInfo> list= viewUserInfoService.selectAll(viewUserInfo);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("username", "用户名");
        header.put("truename", "真实姓名");
        header.put("password", "密码");
        header.put("phone", "电话");
        header.put("email", "邮箱");
        header.put("address", "地址");
//		header.put("sex_", "{\"name\":\"性别\",\"0\":\"男\",\"1\":\"女\"}");
        header.put("photoid", "图片ID");
		header.put("brithday_", "出生日期");
		header.put("createtime_", "创建时间");
		header.put("lastlogin_", "最后登录时间");
        header.put("itype", "用户类型");
        header.put("schoolid", "学校id");
        header.put("userId", "");
        header.put("roleId", "");
        header.put("roleName", "");
        header.put("roleNum", "");
        header.put("companyId", "");
        header.put("companyName", "公司名称");
        header.put("companyAddress", "公司地址");
        header.put("companyPhone", "公司电话");
        header.put("schoolId", "学校");
        header.put("schoolName", "名称");
        header.put("schoolAddress", "地址");
        header.put("companyIntro", "");
        header.put("schoolIntro", "简介");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("viewUserInfo/gotoList")
	public String gotoList(ViewUserInfo viewUserInfo, HttpServletRequest request, HttpServletResponse response){
		return "sys/view_user_info_list";
	}


	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("viewUserInfo/gotoDetail")
	//@Auth("viewUserInfo/save")
	public String gotoDetail(ViewUserInfo viewUserInfo, HttpServletRequest request, HttpServletResponse response,Model model){
		if(viewUserInfo.getId()!=null){
			request.setAttribute("view_user_info",viewUserInfoService.selectByPrimaryKey(viewUserInfo));
		}else {
			request.setAttribute("view_user_info",viewUserInfo);
		}
		model.addAttribute("schools",schoolService.selectAll(null));
		model.addAttribute("companys",companyService.selectAll(null));

		return "sys/view_user_info_detail";
	}

	@RequestMapping("viewUserInfo/gotoDetailTea")
	//@Auth("viewUserInfo/save")
	public String gotoDetailTea(ViewUserInfo viewUserInfo, HttpServletRequest request, HttpServletResponse response,Model model){
		if(viewUserInfo.getId()!=null){
			request.setAttribute("view_user_info",viewUserInfoService.selectByPrimaryKey(viewUserInfo));
		}else {
			request.setAttribute("view_user_info",viewUserInfo);
		}
		model.addAttribute("schools",schoolService.selectAll(null));
		model.addAttribute("companys",companyService.selectAll(null));
		return "sys/view_user_info_detail_tea";
	}

	@RequestMapping("viewUserInfo/gotoDetailCom")
	//@Auth("viewUserInfo/save")
	public String gotoDetailCom(ViewUserInfo viewUserInfo, HttpServletRequest request, HttpServletResponse response,Model model){
		if(viewUserInfo.getId()!=null){
			request.setAttribute("view_user_info",viewUserInfoService.selectByPrimaryKey(viewUserInfo));
		}else {
			request.setAttribute("view_user_info",viewUserInfo);
		}
		model.addAttribute("schools",schoolService.selectAll(null));
		model.addAttribute("companys",companyService.selectAll(null));
		return "sys/view_user_info_detail_com";
	}

	@RequestMapping("viewUserInfo/gotoDetailStu")
	//@Auth("viewUserInfo/save")
	public String gotoDetailStu(ViewUserInfo viewUserInfo, HttpServletRequest request, HttpServletResponse response,Model model){
		if(viewUserInfo.getId()!=null){
			request.setAttribute("view_user_info",viewUserInfoService.selectByPrimaryKey(viewUserInfo));
		}else {
			request.setAttribute("view_user_info",viewUserInfo);
		}
		model.addAttribute("schools",schoolService.selectAll(null));
		model.addAttribute("companys",companyService.selectAll(null));
		return "sys/view_user_info_detail_stu";
	}
}

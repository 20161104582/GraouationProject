package com.web.sys.controller;

import javax.annotation.Resource;

import com.web.common.utils.ExcelUtils;
import com.web.sys.bean.SysUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import com.web.sys.bean.ViewSchoolInfo;
import com.web.sys.service.ViewSchoolInfoService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ViewSchoolInfoController {
	
	@Resource
	private ViewSchoolInfoService viewSchoolInfoService;

	/**
	 * 删除
	 * @param viewSchoolInfo
	 * @return
	 */
	@RequestMapping("viewSchoolInfo/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ViewSchoolInfo viewSchoolInfo, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(viewSchoolInfoService.deleteByPrimaryKey(viewSchoolInfo));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param viewSchoolInfo
	 * @return
	 */
	@RequestMapping("viewSchoolInfo/save")
	@ResponseBody
	public DataRes save(ViewSchoolInfo viewSchoolInfo, HttpServletRequest request, HttpServletResponse response){
		if(viewSchoolInfo.getId()==null){
			return DataRes.success(viewSchoolInfoService.insert(viewSchoolInfo));
		}
		return DataRes.success(viewSchoolInfoService.update(viewSchoolInfo));
	}

    /**
     * 根据主键查询
     * @param viewSchoolInfo
     * @return
     */
	@RequestMapping("viewSchoolInfo/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ViewSchoolInfo viewSchoolInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewSchoolInfoService.selectByPrimaryKey(viewSchoolInfo));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("viewSchoolInfo/queryViewSchoolInfoByCondition")
	@ResponseBody
	public DataRes queryByCondition(ViewSchoolInfo viewSchoolInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewSchoolInfoService.queryByCondition(viewSchoolInfo));
    }

   /**
	* 分页查询
	* @param viewSchoolInfo 参数
	* @return
	*/
   @RequestMapping("viewSchoolInfo/selectAll")
   @ResponseBody
   public DataRes selectAll(ViewSchoolInfo viewSchoolInfo,HttpServletRequest request, HttpServletResponse response){
	   return DataRes.success(viewSchoolInfoService.selectAllByPaging(viewSchoolInfo));
   }

	@RequestMapping("viewSchoolInfo/selectAllMy")
	@ResponseBody
	public DataRes selectAllMy(ViewSchoolInfo viewSchoolInfo,HttpServletRequest request, HttpServletResponse response){
		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		viewSchoolInfo.setUserId(user.getId());
		return DataRes.success(viewSchoolInfoService.selectAllByPaging(viewSchoolInfo));
	}

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("viewSchoolInfo/export")
	public void export(ViewSchoolInfo viewSchoolInfo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ViewSchoolInfo> list= viewSchoolInfoService.selectAll(viewSchoolInfo);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("schoolId", "学校");
        header.put("name", "名称");
        header.put("address", "地址");
        header.put("userId", "主键ID");
        header.put("truename", "真实姓名");
        header.put("intro", "简介");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("viewSchoolInfo/gotoList")
	public String gotoList(ViewSchoolInfo viewSchoolInfo, HttpServletRequest request, HttpServletResponse response){
		return "sys/view_school_info_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("viewSchoolInfo/gotoDetail")
	//@Auth("viewSchoolInfo/save")
	public String gotoDetail(ViewSchoolInfo viewSchoolInfo, HttpServletRequest request, HttpServletResponse response){
		if(viewSchoolInfo.getId()!=null){
			request.setAttribute("view_school_info",viewSchoolInfoService.selectByPrimaryKey(viewSchoolInfo));
		}else {
			request.setAttribute("view_school_info",viewSchoolInfo);
		}
		return "sys/view_school_info_detail";
	}
}

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
import com.web.sys.bean.ViewCompanyInfo;
import com.web.sys.service.ViewCompanyInfoService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ViewCompanyInfoController {
	
	@Resource
	private ViewCompanyInfoService viewCompanyInfoService;

	/**
	 * 删除
	 * @param viewCompanyInfo
	 * @return
	 */
	@RequestMapping("viewCompanyInfo/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ViewCompanyInfo viewCompanyInfo, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(viewCompanyInfoService.deleteByPrimaryKey(viewCompanyInfo));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param viewCompanyInfo
	 * @return
	 */
	@RequestMapping("viewCompanyInfo/save")
	@ResponseBody
	public DataRes save(ViewCompanyInfo viewCompanyInfo, HttpServletRequest request, HttpServletResponse response){
		if(viewCompanyInfo.getId()==null){
			return DataRes.success(viewCompanyInfoService.insert(viewCompanyInfo));
		}
		return DataRes.success(viewCompanyInfoService.update(viewCompanyInfo));
	}

    /**
     * 根据主键查询
     * @param viewCompanyInfo
     * @return
     */
	@RequestMapping("viewCompanyInfo/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ViewCompanyInfo viewCompanyInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewCompanyInfoService.selectByPrimaryKey(viewCompanyInfo));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("viewCompanyInfo/queryViewCompanyInfoByCondition")
	@ResponseBody
	public DataRes queryByCondition(ViewCompanyInfo viewCompanyInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewCompanyInfoService.queryByCondition(viewCompanyInfo));
    }

   /**
	* 分页查询
	* @param viewCompanyInfo 参数
	* @return
	*/
   @RequestMapping("viewCompanyInfo/selectAll")
   @ResponseBody
   public DataRes selectAll(ViewCompanyInfo viewCompanyInfo,HttpServletRequest request, HttpServletResponse response){
	   SysUser user = (SysUser)(request.getSession().getAttribute("user"));
	   return DataRes.success(viewCompanyInfoService.selectAllByPaging(viewCompanyInfo));
   }

	@RequestMapping("viewCompanyInfo/selectAllMy")
	@ResponseBody
	public DataRes selectAllMy(ViewCompanyInfo viewCompanyInfo,HttpServletRequest request, HttpServletResponse response){
		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		if(user == null){
			return DataRes.error("-1","未登录");
		}
		viewCompanyInfo.setUserId(user.getId());
		return DataRes.success(viewCompanyInfoService.selectAllByPaging(viewCompanyInfo));
	}

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("viewCompanyInfo/export")
	public void export(ViewCompanyInfo viewCompanyInfo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ViewCompanyInfo> list= viewCompanyInfoService.selectAll(viewCompanyInfo);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("companyId", "");
        header.put("name", "公司名称");
        header.put("address", "公司地址");
        header.put("phone", "公司电话");
        header.put("userId", "主键ID");
        header.put("truename", "真实姓名");
        header.put("intro", "");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("viewCompanyInfo/gotoList")
	public String gotoList(ViewCompanyInfo viewCompanyInfo, HttpServletRequest request, HttpServletResponse response){
		return "sys/view_company_info_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("viewCompanyInfo/gotoDetail")
	//@Auth("viewCompanyInfo/save")
	public String gotoDetail(ViewCompanyInfo viewCompanyInfo, HttpServletRequest request, HttpServletResponse response){
		if(viewCompanyInfo.getId()!=null){
			request.setAttribute("view_company_info",viewCompanyInfoService.selectByPrimaryKey(viewCompanyInfo));
		}else {
			request.setAttribute("view_company_info",viewCompanyInfo);
		}
		return "sys/view_company_info_detail";
	}
}

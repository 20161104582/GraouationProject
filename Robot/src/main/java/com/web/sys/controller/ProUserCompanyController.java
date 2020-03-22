package com.web.sys.controller;

import javax.annotation.Resource;

import com.web.common.utils.ExcelUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import com.web.sys.bean.ProUserCompany;
import com.web.sys.service.ProUserCompanyService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ProUserCompanyController {
	
	@Resource
	private ProUserCompanyService proUserCompanyService;

	/**
	 * 删除
	 * @param proUserCompany
	 * @return
	 */
	@RequestMapping("proUserCompany/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ProUserCompany proUserCompany, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proUserCompanyService.deleteByPrimaryKey(proUserCompany));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param proUserCompany
	 * @return
	 */
	@RequestMapping("proUserCompany/save")
	@ResponseBody
	public DataRes save(ProUserCompany proUserCompany, HttpServletRequest request, HttpServletResponse response){
		if(proUserCompany.getId()==null){
			return DataRes.success(proUserCompanyService.insert(proUserCompany));
		}
		return DataRes.success(proUserCompanyService.update(proUserCompany));
	}

    /**
     * 根据主键查询
     * @param proUserCompany
     * @return
     */
	@RequestMapping("proUserCompany/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ProUserCompany proUserCompany, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proUserCompanyService.selectByPrimaryKey(proUserCompany));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("proUserCompany/queryProUserCompanyByCondition")
	@ResponseBody
	public DataRes queryByCondition(ProUserCompany proUserCompany, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proUserCompanyService.queryByCondition(proUserCompany));
    }

   /**
	* 分页查询
	* @param proUserCompany 参数
	* @return
	*/
	@RequestMapping("proUserCompany/selectAll")
	@ResponseBody
	public DataRes selectAll(ProUserCompany proUserCompany,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proUserCompanyService.selectAllByPaging(proUserCompany));
    }

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("proUserCompany/export")
	public void export(ProUserCompany proUserCompany,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProUserCompany> list= proUserCompanyService.selectAll(proUserCompany);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("userId", "");
        header.put("companyId", "公司ID");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("proUserCompany/gotoList")
	public String gotoList(ProUserCompany proUserCompany, HttpServletRequest request, HttpServletResponse response){
		return "sys/pro_user_company_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("proUserCompany/gotoDetail")
	//@Auth("proUserCompany/save")
	public String gotoDetail(ProUserCompany proUserCompany, HttpServletRequest request, HttpServletResponse response){
		if(proUserCompany.getId()!=null){
			request.setAttribute("pro_user_company",proUserCompanyService.selectByPrimaryKey(proUserCompany));
		}else {
			request.setAttribute("pro_user_company",proUserCompany);
		}
		return "sys/pro_user_company_detail";
	}
}

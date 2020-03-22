package com.web.sys.controller;

import javax.annotation.Resource;

import com.web.common.utils.ExcelUtils;
import com.web.sys.bean.SysUser;
import com.web.sys.bean.ViewCompanyInfo;
import com.web.sys.service.SysUserService;
import com.web.sys.service.ViewCompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import com.web.sys.bean.ProCompany;
import com.web.sys.service.ProCompanyService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ProCompanyController {
	
	@Resource
	private ProCompanyService proCompanyService;

	/**
	 * 删除
	 * @param proCompany
	 * @return
	 */
	@RequestMapping("proCompany/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ProCompany proCompany, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proCompanyService.deleteByPrimaryKey(proCompany));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param proCompany
	 * @return
	 */
	@RequestMapping("proCompany/save")
	@ResponseBody
	public DataRes save(ProCompany proCompany, HttpServletRequest request, HttpServletResponse response){
		if(proCompany.getId()==null){
			return DataRes.success(proCompanyService.insert(proCompany));
		}
		return DataRes.success(proCompanyService.update(proCompany));
	}

    /**
     * 根据主键查询
     * @param proCompany
     * @return
     */
	@RequestMapping("proCompany/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ProCompany proCompany, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proCompanyService.selectByPrimaryKey(proCompany));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("proCompany/queryProCompanyByCondition")
	@ResponseBody
	public DataRes queryByCondition(ProCompany proCompany, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proCompanyService.queryByCondition(proCompany));
    }

   /**
	* 分页查询
	* @param proCompany 参数
	* @return
	*/
   @RequestMapping("proCompany/selectAll")
   @ResponseBody
   public DataRes selectAll(ProCompany proCompany,HttpServletRequest request, HttpServletResponse response){
	   return DataRes.success(proCompanyService.selectAllByPaging(proCompany));
   }


	@RequestMapping("proCompany/selectAllMy")
	@ResponseBody
	public DataRes selectAllMy(ProCompany proCompany,HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proCompanyService.selectAllByPaging(proCompany));
	}

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("proCompany/export")
	public void export(ProCompany proCompany,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProCompany> list= proCompanyService.selectAll(proCompany);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("name", "公司名称");
        header.put("address", "公司地址");
        header.put("phone", "公司电话");
        header.put("intro", "");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("proCompany/gotoList")
	public String gotoList(ProCompany proCompany, HttpServletRequest request, HttpServletResponse response){
		return "sys/pro_company_list";
	}

	@Autowired
	SysUserService userService;

	@Autowired
	ViewCompanyInfoService companyInfoService;
	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("proCompany/gotoDetail")
	//@Auth("proCompany/save")
	public String gotoDetail(ProCompany proCompany, HttpServletRequest request, HttpServletResponse response, Model model){
		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		List<SysUser> nullChargers = userService.queryNullCharger(user);
		request.setAttribute("nullChargers",nullChargers);

		ViewCompanyInfo vci = new ViewCompanyInfo();
		vci.setUserId(user.getId());
		List<ViewCompanyInfo> vcis =companyInfoService.queryByCondition(vci);
		if(vcis != null && vcis.size() > 0){
			vci = vcis.get(0);
		}
		request.setAttribute("vci",vci);
		if(proCompany.getId()!=null){
			request.setAttribute("pro_company",proCompanyService.selectByPrimaryKey(proCompany));
		}else {
			request.setAttribute("pro_company",proCompany);
		}
		return "sys/pro_company_detail";
	}
}

package com.web.sys.controller;

import javax.annotation.Resource;

import com.web.common.utils.ExcelUtils;
import com.web.sys.bean.ProCompany;
import com.web.sys.bean.SysUser;
import com.web.sys.bean.ViewMatchInfo;
import com.web.sys.service.ProCompanyService;
import com.web.sys.service.SysUserService;
import com.web.sys.service.ViewMatchInfoService;
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
import com.web.sys.bean.ProMatch;
import com.web.sys.service.ProMatchService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ProMatchController {
	
	@Resource
	private ProMatchService proMatchService;

	/**
	 * 删除
	 * @param proMatch
	 * @return
	 */
	@RequestMapping("proMatch/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ProMatch proMatch, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proMatchService.deleteByPrimaryKey(proMatch));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param proMatch
	 * @return
	 */
	@RequestMapping("proMatch/save")
	@ResponseBody
	public DataRes save(ProMatch proMatch, HttpServletRequest request, HttpServletResponse response){
		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		if(proMatch.getId()==null){
			proMatch.setUserId(user.getId());
			proMatch.setPubtime(new Date());
			return DataRes.success(proMatchService.insert(proMatch));
		}
		return DataRes.success(proMatchService.update(proMatch));
	}

    /**
     * 根据主键查询
     * @param proMatch
     * @return
     */
	@RequestMapping("proMatch/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ProMatch proMatch, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proMatchService.selectByPrimaryKey(proMatch));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("proMatch/queryProMatchByCondition")
	@ResponseBody
	public DataRes queryByCondition(ProMatch proMatch, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proMatchService.queryByCondition(proMatch));
    }

   /**
	* 分页查询
	* @param proMatch 参数
	* @return
	*/
	@RequestMapping("proMatch/selectAll")
	@ResponseBody
	public DataRes selectAll(ProMatch proMatch,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proMatchService.selectAllByPaging(proMatch));
    }

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("proMatch/export")
	public void export(ProMatch proMatch,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProMatch> list= proMatchService.selectAll(proMatch);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "比赛ID");
        header.put("name", "比赛名称");
		header.put("pubtime_", "发布时间");
		header.put("time_", "比赛时间");
        header.put("userId", "");
        header.put("address", "比赛地点");
		ExcelUtils.exportExcel("比赛ID",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("proMatch/gotoList")
	public String gotoList(ProMatch proMatch, HttpServletRequest request, HttpServletResponse response){
		return "sys/pro_match_list";
	}

	@Autowired
	SysUserService userService;

	@Autowired
	ProMatchService matchService;

	@Autowired
	ViewMatchInfoService matchInfoService;

	@Autowired
	ProCompanyService companyService;
	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("proMatch/gotoDetail")
	//@Auth("proMatch/save")
	public String gotoDetail(ProMatch proMatch, HttpServletRequest request, HttpServletResponse response){
		List<ProCompany> companys = companyService.selectAll(null);
		request.setAttribute("companys", companys);
		ViewMatchInfo vmi = new ViewMatchInfo();
		vmi.setMatchId(proMatch.getId());
		List<ViewMatchInfo> vmis = matchInfoService.queryByCondition(vmi);
		if(vmis != null && vmis.size() > 0) vmi = vmis.get(0);

		request.setAttribute("vmi",vmi);
		if(proMatch.getId()!=null){

			request.setAttribute("pro_match",proMatchService.selectByPrimaryKey(proMatch));
		}else {
			request.setAttribute("pro_match",proMatch);
		}
		return "sys/pro_match_detail";
	}
}

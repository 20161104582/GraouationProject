package com.web.sys.controller;

import javax.annotation.Resource;

import com.web.common.utils.ExcelUtils;
import com.web.sys.bean.SysUser;
import com.web.sys.bean.ViewUserInfo;
import com.web.sys.service.ViewUserInfoService;
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
import com.web.sys.bean.ViewMatchInfo;
import com.web.sys.service.ViewMatchInfoService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ViewMatchInfoController {
	
	@Resource
	private ViewMatchInfoService viewMatchInfoService;

	/**
	 * 删除
	 * @param viewMatchInfo
	 * @return
	 */
	@RequestMapping("viewMatchInfo/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ViewMatchInfo viewMatchInfo, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(viewMatchInfoService.deleteByPrimaryKey(viewMatchInfo));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param viewMatchInfo
	 * @return
	 */
	@RequestMapping("viewMatchInfo/save")
	@ResponseBody
	public DataRes save(ViewMatchInfo viewMatchInfo, HttpServletRequest request, HttpServletResponse response){
		if(viewMatchInfo.getId()==null){
			return DataRes.success(viewMatchInfoService.insert(viewMatchInfo));
		}
		return DataRes.success(viewMatchInfoService.update(viewMatchInfo));
	}

    /**
     * 根据主键查询
     * @param viewMatchInfo
     * @return
     */
	@RequestMapping("viewMatchInfo/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ViewMatchInfo viewMatchInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewMatchInfoService.selectByPrimaryKey(viewMatchInfo));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("viewMatchInfo/queryViewMatchInfoByCondition")
	@ResponseBody
	public DataRes queryByCondition(ViewMatchInfo viewMatchInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewMatchInfoService.queryByCondition(viewMatchInfo));
    }

   /**
	* 分页查询
	* @param viewMatchInfo 参数
	* @return
	*/
   @RequestMapping("viewMatchInfo/selectAll")
   @ResponseBody
   public DataRes selectAll(ViewMatchInfo viewMatchInfo,HttpServletRequest request, HttpServletResponse response){
	   return DataRes.success(viewMatchInfoService.selectAllByPaging(viewMatchInfo));
   }


	@RequestMapping("viewMatchInfo/selectAllCom")
	@ResponseBody
	public DataRes selectAllCom(ViewMatchInfo viewMatchInfo,HttpServletRequest request, HttpServletResponse response){

		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		viewMatchInfo.setTruename(user.getTruename());
		return DataRes.success(viewMatchInfoService.selectAllByPaging(viewMatchInfo));
	}

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("viewMatchInfo/export")
	public void export(ViewMatchInfo viewMatchInfo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ViewMatchInfo> list= viewMatchInfoService.selectAll(viewMatchInfo);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("matchId", "比赛ID");
        header.put("matchName", "比赛名称");
		header.put("matchPubtime_", "发布时间");
		header.put("matchTime_", "比赛时间");
        header.put("matchAddress", "比赛地点");
        header.put("truename", "真实姓名");
        header.put("phone", "电话");
        header.put("companyName", "公司名称");
        header.put("companyAddress", "公司地址");
        header.put("companyIntro", "");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("viewMatchInfo/gotoList")
	public String gotoList(ViewMatchInfo viewMatchInfo, HttpServletRequest request, HttpServletResponse response){
		return "sys/view_match_info_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("viewMatchInfo/gotoDetail")
	//@Auth("viewMatchInfo/save")
	public String gotoDetail(ViewMatchInfo viewMatchInfo, HttpServletRequest request, HttpServletResponse response){
		if(viewMatchInfo.getId()!=null){
			request.setAttribute("view_match_info",viewMatchInfoService.selectByPrimaryKey(viewMatchInfo));
		}else {
			request.setAttribute("view_match_info",viewMatchInfo);
		}
		return "sys/view_match_info_detail";
	}

	@Autowired
	ViewUserInfoService userInfoService;

	@RequestMapping("viewMatchInfo/gotoDetailSign")
	//@Auth("viewMatchInfo/save")
	public String gotoDetailSign(ViewMatchInfo viewMatchInfo, HttpServletRequest request, HttpServletResponse response){
		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		ViewUserInfo teacher = new ViewUserInfo();
		teacher.setUserId(user.getId());
		List<ViewUserInfo> teas = userInfoService.queryByCondition(teacher);
		teacher = teas.get(0);

		ViewUserInfo stu = new ViewUserInfo();
		stu.setRoleNum(4L);
		stu.setSchoolId(teacher.getSchoolId());
		List<ViewUserInfo> stus = userInfoService.queryByCondition(stu);
		request.setAttribute("stus",stus);

		if(viewMatchInfo.getId()!=null){
			request.setAttribute("view_match_info",viewMatchInfoService.selectByPrimaryKey(viewMatchInfo));
		}else {
			request.setAttribute("view_match_info",viewMatchInfo);
		}
		return "sys/view_match_info_detail_sign";
	}

	@RequestMapping("viewMatchInfo/gotoDetailSignShow")
	//@Auth("viewMatchInfo/save")
	public String gotoDetailSignShow(ViewMatchInfo viewMatchInfo, HttpServletRequest request, HttpServletResponse response){
		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		ViewUserInfo teacher = new ViewUserInfo();
		teacher.setUserId(user.getId());
		List<ViewUserInfo> teas = userInfoService.queryByCondition(teacher);
		teacher = teas.get(0);

		ViewUserInfo stu = new ViewUserInfo();
		stu.setRoleNum(4L);
		stu.setSchoolId(teacher.getSchoolId());
		List<ViewUserInfo> stus = userInfoService.queryByCondition(stu);
		request.setAttribute("stus",stus);

		if(viewMatchInfo.getId()!=null){
			request.setAttribute("view_match_info",viewMatchInfoService.selectByPrimaryKey(viewMatchInfo));
		}else {
			request.setAttribute("view_match_info",viewMatchInfo);
		}
		return "sys/view_match_info_detail_sign_show";
	}
}

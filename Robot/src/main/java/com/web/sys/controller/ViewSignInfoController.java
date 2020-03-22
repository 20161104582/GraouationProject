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
import com.web.sys.bean.ViewSignInfo;
import com.web.sys.service.ViewSignInfoService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ViewSignInfoController {
	
	@Resource
	private ViewSignInfoService viewSignInfoService;

	/**
	 * 删除
	 * @param viewSignInfo
	 * @return
	 */
	@RequestMapping("viewSignInfo/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ViewSignInfo viewSignInfo, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(viewSignInfoService.deleteByPrimaryKey(viewSignInfo));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param viewSignInfo
	 * @return
	 */
	@RequestMapping("viewSignInfo/save")
	@ResponseBody
	public DataRes save(ViewSignInfo viewSignInfo, HttpServletRequest request, HttpServletResponse response){
		if(viewSignInfo.getId()==null){
			return DataRes.success(viewSignInfoService.insert(viewSignInfo));
		}
		return DataRes.success(viewSignInfoService.update(viewSignInfo));
	}

    /**
     * 根据主键查询
     * @param viewSignInfo
     * @return
     */
	@RequestMapping("viewSignInfo/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ViewSignInfo viewSignInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewSignInfoService.selectByPrimaryKey(viewSignInfo));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("viewSignInfo/queryViewSignInfoByCondition")
	@ResponseBody
	public DataRes queryByCondition(ViewSignInfo viewSignInfo, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(viewSignInfoService.queryByCondition(viewSignInfo));
    }

   /**
	* 分页查询
	* @param viewSignInfo 参数
	* @return
	*/
   @RequestMapping("viewSignInfo/selectAll")
   @ResponseBody
   public DataRes selectAll(ViewSignInfo viewSignInfo,HttpServletRequest request, HttpServletResponse response){
	   return DataRes.success(viewSignInfoService.selectAllByPaging(viewSignInfo));
   }


	@RequestMapping("viewSignInfo/selectAllTea")
	@ResponseBody
	public DataRes selectAllTea(ViewSignInfo viewSignInfo,HttpServletRequest request, HttpServletResponse response){
		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		viewSignInfo.setTeacherId(user.getId());
		return DataRes.success(viewSignInfoService.selectAllByPaging(viewSignInfo));
	}

	@RequestMapping("viewSignInfo/selectAllStu")
	@ResponseBody
	public DataRes selectAllStu(ViewSignInfo viewSignInfo,HttpServletRequest request, HttpServletResponse response){
		SysUser user = (SysUser)(request.getSession().getAttribute("user"));

		return DataRes.success(viewSignInfoService.queryStudents(user));
	}

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("viewSignInfo/export")
	public void export(ViewSignInfo viewSignInfo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ViewSignInfo> list= viewSignInfoService.selectAll(viewSignInfo);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("signId", "");
        header.put("matchId", "");
        header.put("teacherId", "");
		header.put("signTime_", "报名时间");
        header.put("score", "最终成绩");
        header.put("fileId", "结果文件");
        header.put("matchName", "比赛名称");
		header.put("matchPubtime_", "发布时间");
		header.put("matchTime_", "比赛时间");
        header.put("matchAddress", "比赛地点");
        header.put("chargerName", "真实姓名");
        header.put("teacherName", "真实姓名");
        header.put("schoolName", "名称");
        header.put("schoolAddress", "地址");
        header.put("companyName", "公司名称");
        header.put("companyAddress", "公司地址");
        header.put("companyPhone", "公司电话");
        header.put("schoolIntro", "简介");
        header.put("companyIntro", "");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("viewSignInfo/gotoList")
	public String gotoList(ViewSignInfo viewSignInfo, HttpServletRequest request, HttpServletResponse response){
		return "sys/view_sign_info_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("viewSignInfo/gotoDetail")
	//@Auth("viewSignInfo/save")
	public String gotoDetail(ViewSignInfo viewSignInfo, HttpServletRequest request, HttpServletResponse response){
		if(viewSignInfo.getId()!=null){
			request.setAttribute("view_sign_info",viewSignInfoService.selectByPrimaryKey(viewSignInfo));
		}else {
			request.setAttribute("view_sign_info",viewSignInfo);
		}
		return "sys/view_sign_info_detail";
	}
}

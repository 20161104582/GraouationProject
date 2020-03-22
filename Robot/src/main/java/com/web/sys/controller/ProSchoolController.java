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
import com.web.sys.bean.ProSchool;
import com.web.sys.service.ProSchoolService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ProSchoolController {
	
	@Resource
	private ProSchoolService proSchoolService;

	/**
	 * 删除
	 * @param proSchool
	 * @return
	 */
	@RequestMapping("proSchool/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ProSchool proSchool, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proSchoolService.deleteByPrimaryKey(proSchool));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param proSchool
	 * @return
	 */
	@RequestMapping("proSchool/save")
	@ResponseBody
	public DataRes save(ProSchool proSchool, HttpServletRequest request, HttpServletResponse response){
		if(proSchool.getId()==null){
			return DataRes.success(proSchoolService.insert(proSchool));
		}
		return DataRes.success(proSchoolService.update(proSchool));
	}

    /**
     * 根据主键查询
     * @param proSchool
     * @return
     */
	@RequestMapping("proSchool/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ProSchool proSchool, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSchoolService.selectByPrimaryKey(proSchool));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("proSchool/queryProSchoolByCondition")
	@ResponseBody
	public DataRes queryByCondition(ProSchool proSchool, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSchoolService.queryByCondition(proSchool));
    }

   /**
	* 分页查询
	* @param proSchool 参数
	* @return
	*/
	@RequestMapping("proSchool/selectAll")
	@ResponseBody
	public DataRes selectAll(ProSchool proSchool,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSchoolService.selectAllByPaging(proSchool));
    }

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("proSchool/export")
	public void export(ProSchool proSchool,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProSchool> list= proSchoolService.selectAll(proSchool);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "学校");
        header.put("name", "名称");
        header.put("address", "地址");
        header.put("intro", "简介");
		ExcelUtils.exportExcel("学校",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("proSchool/gotoList")
	public String gotoList(ProSchool proSchool, HttpServletRequest request, HttpServletResponse response){
		return "sys/pro_school_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("proSchool/gotoDetail")
	//@Auth("proSchool/save")
	public String gotoDetail(ProSchool proSchool, HttpServletRequest request, HttpServletResponse response){
		if(proSchool.getId()!=null){
			request.setAttribute("pro_school",proSchoolService.selectByPrimaryKey(proSchool));
		}else {
			request.setAttribute("pro_school",proSchool);
		}
		return "sys/pro_school_detail";
	}
}

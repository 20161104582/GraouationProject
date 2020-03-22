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
import com.web.sys.bean.ProUserSchool;
import com.web.sys.service.ProUserSchoolService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ProUserSchoolController {
	
	@Resource
	private ProUserSchoolService proUserSchoolService;

	/**
	 * 删除
	 * @param proUserSchool
	 * @return
	 */
	@RequestMapping("proUserSchool/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ProUserSchool proUserSchool, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proUserSchoolService.deleteByPrimaryKey(proUserSchool));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param proUserSchool
	 * @return
	 */
	@RequestMapping("proUserSchool/save")
	@ResponseBody
	public DataRes save(ProUserSchool proUserSchool, HttpServletRequest request, HttpServletResponse response){
		if(proUserSchool.getId()==null){
			return DataRes.success(proUserSchoolService.insert(proUserSchool));
		}
		return DataRes.success(proUserSchoolService.update(proUserSchool));
	}

    /**
     * 根据主键查询
     * @param proUserSchool
     * @return
     */
	@RequestMapping("proUserSchool/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ProUserSchool proUserSchool, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proUserSchoolService.selectByPrimaryKey(proUserSchool));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("proUserSchool/queryProUserSchoolByCondition")
	@ResponseBody
	public DataRes queryByCondition(ProUserSchool proUserSchool, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proUserSchoolService.queryByCondition(proUserSchool));
    }

   /**
	* 分页查询
	* @param proUserSchool 参数
	* @return
	*/
	@RequestMapping("proUserSchool/selectAll")
	@ResponseBody
	public DataRes selectAll(ProUserSchool proUserSchool,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proUserSchoolService.selectAllByPaging(proUserSchool));
    }

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("proUserSchool/export")
	public void export(ProUserSchool proUserSchool,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProUserSchool> list= proUserSchoolService.selectAll(proUserSchool);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("userId", "");
        header.put("schoolId", "学校ID");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("proUserSchool/gotoList")
	public String gotoList(ProUserSchool proUserSchool, HttpServletRequest request, HttpServletResponse response){
		return "sys/pro_user_school_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("proUserSchool/gotoDetail")
	//@Auth("proUserSchool/save")
	public String gotoDetail(ProUserSchool proUserSchool, HttpServletRequest request, HttpServletResponse response){
		if(proUserSchool.getId()!=null){
			request.setAttribute("pro_user_school",proUserSchoolService.selectByPrimaryKey(proUserSchool));
		}else {
			request.setAttribute("pro_user_school",proUserSchool);
		}
		return "sys/pro_user_school_detail";
	}
}

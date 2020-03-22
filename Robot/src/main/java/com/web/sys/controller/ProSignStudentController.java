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
import com.web.sys.bean.ProSignStudent;
import com.web.sys.service.ProSignStudentService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ProSignStudentController {
	
	@Resource
	private ProSignStudentService proSignStudentService;

	/**
	 * 删除
	 * @param proSignStudent
	 * @return
	 */
	@RequestMapping("proSignStudent/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ProSignStudent proSignStudent, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proSignStudentService.deleteByPrimaryKey(proSignStudent));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param proSignStudent
	 * @return
	 */
	@RequestMapping("proSignStudent/save")
	@ResponseBody
	public DataRes save(ProSignStudent proSignStudent, HttpServletRequest request, HttpServletResponse response){
		if(proSignStudent.getId()==null){
			return DataRes.success(proSignStudentService.insert(proSignStudent));
		}
		return DataRes.success(proSignStudentService.update(proSignStudent));
	}

    /**
     * 根据主键查询
     * @param proSignStudent
     * @return
     */
	@RequestMapping("proSignStudent/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ProSignStudent proSignStudent, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSignStudentService.selectByPrimaryKey(proSignStudent));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("proSignStudent/queryProSignStudentByCondition")
	@ResponseBody
	public DataRes queryByCondition(ProSignStudent proSignStudent, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSignStudentService.queryByCondition(proSignStudent));
    }

   /**
	* 分页查询
	* @param proSignStudent 参数
	* @return
	*/
	@RequestMapping("proSignStudent/selectAll")
	@ResponseBody
	public DataRes selectAll(ProSignStudent proSignStudent,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSignStudentService.selectAllByPaging(proSignStudent));
    }

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("proSignStudent/export")
	public void export(ProSignStudent proSignStudent,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProSignStudent> list= proSignStudentService.selectAll(proSignStudent);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("signId", "");
        header.put("userId", "");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("proSignStudent/gotoList")
	public String gotoList(ProSignStudent proSignStudent, HttpServletRequest request, HttpServletResponse response){
		return "sys/pro_sign_student_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("proSignStudent/gotoDetail")
	//@Auth("proSignStudent/save")
	public String gotoDetail(ProSignStudent proSignStudent, HttpServletRequest request, HttpServletResponse response){
		if(proSignStudent.getId()!=null){
			request.setAttribute("pro_sign_student",proSignStudentService.selectByPrimaryKey(proSignStudent));
		}else {
			request.setAttribute("pro_sign_student",proSignStudent);
		}
		return "sys/pro_sign_student_detail";
	}
}

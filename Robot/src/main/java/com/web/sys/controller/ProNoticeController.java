package com.web.sys.controller;

import javax.annotation.Resource;

import com.web.common.utils.ExcelUtils;
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
import com.web.sys.bean.ProNotice;
import com.web.sys.service.ProNoticeService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ProNoticeController {
	
	@Resource
	private ProNoticeService proNoticeService;

	/**
	 * 删除
	 * @param proNotice
	 * @return
	 */
	@RequestMapping("proNotice/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ProNotice proNotice, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proNoticeService.deleteByPrimaryKey(proNotice));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param proNotice
	 * @return
	 */
	@RequestMapping("proNotice/save")
	@ResponseBody
	public DataRes save(ProNotice proNotice, HttpServletRequest request, HttpServletResponse response){
		if(proNotice.getId()==null){
			proNotice.setPubtime(new Date());
			return DataRes.success(proNoticeService.insert(proNotice));
		}
		return DataRes.success(proNoticeService.update(proNotice));
	}

    /**
     * 根据主键查询
     * @param proNotice
     * @return
     */
	@RequestMapping("proNotice/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ProNotice proNotice, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proNoticeService.selectByPrimaryKey(proNotice));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("proNotice/queryProNoticeByCondition")
	@ResponseBody
	public DataRes queryByCondition(ProNotice proNotice, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proNoticeService.queryByCondition(proNotice));
    }

   /**
	* 分页查询
	* @param proNotice 参数
	* @return
	*/
	@RequestMapping("proNotice/selectAll")
	@ResponseBody
	public DataRes selectAll(ProNotice proNotice,HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proNoticeService.selectAllByPaging(proNotice));
    }

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("proNotice/export")
	public void export(ProNotice proNotice,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProNotice> list= proNoticeService.selectAll(proNotice);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "公告ID");
        header.put("title", "标题");
		header.put("pubtime_", "发布时间");
        header.put("content", "公告内容");
		ExcelUtils.exportExcel("公告ID",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("proNotice/gotoList")
	public String gotoList(ProNotice proNotice, HttpServletRequest request, HttpServletResponse response){
		return "sys/pro_notice_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("proNotice/gotoDetail")
	//@Auth("proNotice/save")
	public String gotoDetail(ProNotice proNotice, HttpServletRequest request, HttpServletResponse response){
		if(proNotice.getId()!=null){
			request.setAttribute("pro_notice",proNoticeService.selectByPrimaryKey(proNotice));
		}else {
			request.setAttribute("pro_notice",proNotice);
		}
		return "sys/pro_notice_detail";
	}
}

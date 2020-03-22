package com.web.sys.controller;

import javax.annotation.Resource;

import com.web.common.utils.ExcelUtils;
import com.web.sys.bean.ProSignStudent;
import com.web.sys.bean.SysUser;
import com.web.sys.bean.ViewMatchInfo;
import com.web.sys.service.ProSignStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.common.bean.DataRes;

import java.util.*;

import com.web.sys.bean.ProSign;
import com.web.sys.service.ProSignService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author wyb
 *
 */
@Controller
public class ProSignController {
	
	@Resource
	private ProSignService proSignService;

	/**
	 * 删除
	 * @param proSign
	 * @return
	 */
	@RequestMapping("proSign/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proSignService.deleteByPrimaryKey(proSign));
	}

    /**
	 * 保存 如果id存在则修改否则新增
	 * @param proSign
	 * @return
	 */
	@RequestMapping("proSign/save")
	@ResponseBody
	public DataRes save(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
		if(proSign.getId()==null){
			return DataRes.success(proSignService.insert(proSign));
		}
		return DataRes.success(proSignService.update(proSign));
	}

	@RequestMapping("proSign/add")
	@ResponseBody
	public DataRes add(ViewMatchInfo matchInfo, String userids, HttpServletRequest request, HttpServletResponse response){
		if("".equals(userids) || userids == null){
			return DataRes.error("-1","请选择报名学生");
		}


		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		String[] useridss = userids.split(",");
		List<Long> uids = new ArrayList<>();
		for(String uid : useridss){
			Long uidl = Long.parseLong(uid);
			uids.add(uidl);
		}
		if(uids.size() < 1){
			return DataRes.error("-1","请选择报名学生");
		}
		ProSign sign = new ProSign();
		sign.setMatchId(matchInfo.getMatchId());
		sign.setSignTime(new Date());
		sign.setTeacherId(user.getId());
		proSignService.insert(sign);
		for(Long uid: uids){
			ProSignStudent ss = new ProSignStudent();
			ss.setSignId(sign.getId());
			ss.setUserId(uid);
			signStudentService.insert(ss);
		}
		return DataRes.success(sign);
	}
	@Autowired
	ProSignStudentService signStudentService;
    /**
     * 根据主键查询
     * @param proSign
     * @return
     */
	@RequestMapping("proSign/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSignService.selectByPrimaryKey(proSign));
    }

    /**
	* 根据条件查询
	*/
	@RequestMapping("proSign/queryProSignByCondition")
	@ResponseBody
	public DataRes queryByCondition(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSignService.queryByCondition(proSign));
    }

   /**
	* 分页查询
	* @param proSign 参数
	* @return
	*/
   @RequestMapping("proSign/selectAll")
   @ResponseBody
   public DataRes selectAll(ProSign proSign,HttpServletRequest request, HttpServletResponse response){
	   return DataRes.success(proSignService.selectAllByPaging(proSign));
   }

	@RequestMapping("proSign/selectAllSign")
	@ResponseBody
	public DataRes selectAllSign(ProSign proSign,HttpServletRequest request, HttpServletResponse response){
		SysUser user = (SysUser)(request.getSession().getAttribute("user"));

		return DataRes.success(proSignService.selectAllByPaging(proSign));
	}

	/**
	* 导出数据
	* @return
	*/
	@RequestMapping("proSign/export")
	public void export(ProSign proSign,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProSign> list= proSignService.selectAll(proSign);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("matchId", "");
        header.put("teacherId", "");
		header.put("signTime_", "报名时间");
        header.put("fileId", "结果文件");
        header.put("score", "最终成绩");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* 跳转到列表页面
	* @return
	*/
	@RequestMapping("proSign/gotoList")
	public String gotoList(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
		return "sys/pro_sign_list";
	}

	/**
	* 跳转到详情页面
	* @return
	*/
	@RequestMapping("proSign/gotoDetail")
	//@Auth("proSign/save")
	public String gotoDetail(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
		if(proSign.getId()!=null){
			request.setAttribute("pro_sign",proSignService.selectByPrimaryKey(proSign));
		}else {
			request.setAttribute("pro_sign",proSign);
		}
		return "sys/pro_sign_detail";
	}
}

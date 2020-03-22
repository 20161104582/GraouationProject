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
	 * ɾ��
	 * @param proSign
	 * @return
	 */
	@RequestMapping("proSign/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(proSignService.deleteByPrimaryKey(proSign));
	}

    /**
	 * ���� ���id�������޸ķ�������
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
			return DataRes.error("-1","��ѡ����ѧ��");
		}


		SysUser user = (SysUser)(request.getSession().getAttribute("user"));
		String[] useridss = userids.split(",");
		List<Long> uids = new ArrayList<>();
		for(String uid : useridss){
			Long uidl = Long.parseLong(uid);
			uids.add(uidl);
		}
		if(uids.size() < 1){
			return DataRes.error("-1","��ѡ����ѧ��");
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
     * ����������ѯ
     * @param proSign
     * @return
     */
	@RequestMapping("proSign/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSignService.selectByPrimaryKey(proSign));
    }

    /**
	* ����������ѯ
	*/
	@RequestMapping("proSign/queryProSignByCondition")
	@ResponseBody
	public DataRes queryByCondition(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(proSignService.queryByCondition(proSign));
    }

   /**
	* ��ҳ��ѯ
	* @param proSign ����
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
	* ��������
	* @return
	*/
	@RequestMapping("proSign/export")
	public void export(ProSign proSign,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProSign> list= proSignService.selectAll(proSign);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "");
        header.put("matchId", "");
        header.put("teacherId", "");
		header.put("signTime_", "����ʱ��");
        header.put("fileId", "����ļ�");
        header.put("score", "���ճɼ�");
		ExcelUtils.exportExcel("",header,list,response,request);
    }

	/**
	* ��ת���б�ҳ��
	* @return
	*/
	@RequestMapping("proSign/gotoList")
	public String gotoList(ProSign proSign, HttpServletRequest request, HttpServletResponse response){
		return "sys/pro_sign_list";
	}

	/**
	* ��ת������ҳ��
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

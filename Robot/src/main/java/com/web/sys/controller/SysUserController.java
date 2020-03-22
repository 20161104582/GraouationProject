package com.web.sys.controller;

import javax.annotation.Resource;

import com.web.common.utils.ExcelUtils;
import com.web.sys.utils.T;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.common.bean.DataRes;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import com.web.sys.bean.SysUser;
import com.web.sys.service.SysUserService;
import org.springframework.stereotype.Controller;
//import com.web.common.annotation.Auth;
/**
 * 
 * @author zengtp
 *
 */
@Controller
public class SysUserController {
	
	@Resource
	private SysUserService sysUserService;

	/**
	 * ɾ��
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("sysUser/deleteByPrimaryKey")
	@ResponseBody
	public DataRes deleteByPrimaryKey(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(sysUserService.deleteByPrimaryKey(sysUser));
	}

    /**
	 * ���� ���id�������޸ķ�������
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("sysUser/save")
	@ResponseBody
	public DataRes save(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		if(sysUser.getId()==null){
			sysUser.setPassword("123");
			return DataRes.success(sysUserService.insert(sysUser));
		}
		return DataRes.success(sysUserService.update(sysUser));
	}

	@RequestMapping("sysUser/reset")
	@ResponseBody
	public DataRes reset(SysUser sysUser){
		sysUser.setPassword("123");
		return DataRes.success(sysUserService.update(sysUser));
	}

	@RequestMapping("sysUser/updatepassword")
	@ResponseBody
	public DataRes updatepassword(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		String p1 = request.getParameter("password1");
		String p2 = request.getParameter("password2");
		String p3 = request.getParameter("password3");

		long idInt;
		try{
			idInt = Integer.parseInt(id);
		}catch (Exception ex){
			return DataRes.error();
		}
		SysUser t = new SysUser();
		t.setId(idInt);
		SysUser user = sysUserService.selectByPrimaryKey(t);
		if(!user.getPassword().equals(p1)){
			return DataRes.error("-1","�����벻��ȷ");
		}
		if(T.isNullOrWhite(p2)||T.isNullOrWhite(p3)||!p2.equals(p3)){
			return DataRes.error("-1","�����������벻һ��");
		}
		user.setPassword(p2);

		return DataRes.success(sysUserService.update(user));
	}

    /**
     * ����������ѯ
     * @param sysUser
     * @return
     */
	@RequestMapping("sysUser/selectByPrimaryKey")
	@ResponseBody
	public DataRes selectByPrimaryKey(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysUserService.selectByPrimaryKey(sysUser));
    }

    /**
	* ����������ѯ
	*/
	@RequestMapping("sysUser/querySysUserByCondition")
	@ResponseBody
	public DataRes queryByCondition(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
    	return DataRes.success(sysUserService.queryByCondition(sysUser));
    }

	/**
	 * ��ҳ��ѯ
	 * @param sysUser ����
	 * @return
	 */
	@RequestMapping("sysUser/selectAll")
	@ResponseBody
	public DataRes selectAll(SysUser sysUser,HttpServletRequest request, HttpServletResponse response){
		return DataRes.success(sysUserService.selectAllByPaging(sysUser));
	}

	/**
	 * ��ҳ��ѯ
	 * @param sysUser ����
	 * @return
	 */
	@RequestMapping("sysUser/selectAllStu")
	@ResponseBody
	public DataRes selectAllStu(SysUser sysUser,HttpServletRequest request, HttpServletResponse response){
		sysUser.setItype((short)0);
		return DataRes.success(sysUserService.selectAllByPaging(sysUser));
	}

	@RequestMapping("sysUser/selectAllTea")
	@ResponseBody
	public DataRes selectAllTea(SysUser sysUser,HttpServletRequest request, HttpServletResponse response){
		sysUser.setItype((short)1);
		return DataRes.success(sysUserService.selectAllByPaging(sysUser));
	}

	/**
	* ��������
	* @param tests ����
	* @return
	*/
	@RequestMapping("sysUser/export")
	public void export(SysUser sysUser,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysUser> list= sysUserService.selectAll(sysUser);
		Map<String, String> header = new LinkedHashMap<>();
        header.put("id", "����ID");
        header.put("username", "�û���");
        header.put("truename", "��ʵ����");
        header.put("password", "����");
        header.put("phone", "�绰");
        header.put("email", "����");
        header.put("address", "��ַ");
//		header.put("sex_", "{\"name\":\"�Ա�\",\"0\":\"��\",\"1\":\"Ů\"}");
		header.put("sex", "�Ա�");
        header.put("photoid", "ͼƬID");
		header.put("brithday_", "��������");
		header.put("createtime_", "����ʱ��");
		header.put("lastlogin_", "����¼ʱ��");
		ExcelUtils.exportExcel("�û���",header,list,response,request);
    }

	/**
	* ��ת���б�ҳ��
	* @return
	*/
	@RequestMapping("sysUser/gotoList")
	public String gotoList(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		return "sys/sys_user_list";
	}

	/**
	* ��ת������ҳ��
	* @return
	*/
	@RequestMapping("sysUser/gotoDetail")
	//@Auth("sysUser/save")
	public String gotoDetail(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		if(sysUser.getId()!=null){
			request.setAttribute("sys_user",sysUserService.selectByPrimaryKey(sysUser));
		}else {
			request.setAttribute("sys_user",sysUser);
		}
		return "sys/sys_user_detail";
	}
	@RequestMapping("sysUser/gotoTeaDetail")
	//@Auth("sysUser/save")
	public String gotoTeaDetail(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		if(sysUser.getId()!=null){
			request.setAttribute("sys_user",sysUserService.selectByPrimaryKey(sysUser));
		}else {
			request.setAttribute("sys_user",sysUser);
		}
		return "sys/sys_user_tea_detail";
	}

	@RequestMapping("sysUser/gotoStuDetail")
	//@Auth("sysUser/save")
	public String gotoStuDetail(SysUser sysUser, HttpServletRequest request, HttpServletResponse response){
		if(sysUser.getId()!=null){
			request.setAttribute("sys_user",sysUserService.selectByPrimaryKey(sysUser));
		}else {
			request.setAttribute("sys_user",sysUser);
		}
		return "sys/sys_user_stu_detail";
	}

}

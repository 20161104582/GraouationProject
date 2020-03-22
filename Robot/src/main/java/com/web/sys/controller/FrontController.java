package com.web.sys.controller;


import com.web.common.bean.DataRes;
import com.web.sys.bean.ProNotice;
import com.web.sys.bean.SysUser;
import com.web.sys.service.ProNoticeService;
import com.web.sys.service.SysUserRoleService;
import com.web.sys.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/front")
public class FrontController {

    @Autowired
    SysUserService userService;

    @Autowired
    SysUserRoleService user_roleService;

    @RequestMapping(value = {"/",""},method=RequestMethod.GET)
    public String toLogin(HttpServletRequest request, Model model){
        return "front/index";
    }

    @RequestMapping(value = {"/{path}/{name}"},method=RequestMethod.GET)
    public String personal(HttpServletRequest request, Model model,@PathVariable String path,@PathVariable String name){
        return path + "/" + name;
    }

    @RequestMapping(value = {"/notice"},method=RequestMethod.GET)
    public String notice(HttpServletRequest request, Model model){
        model.addAttribute("notices",proNoticeService.selectAll(null));
        return "front/notice" ;
    }

    @Autowired
    ProNoticeService proNoticeService;

    @RequestMapping("notice/detail")
    //@Auth("proNotice/save")
    public String gotoDetail(ProNotice proNotice, HttpServletRequest request, HttpServletResponse response){
        if(proNotice.getId()!=null){
            request.setAttribute("pro_notice",proNoticeService.selectByPrimaryKey(proNotice));
        }else {
            request.setAttribute("pro_notice",proNotice);
        }
        return "front/notice_detail";
    }
    //≈‰÷√404“≥√Ê
    @RequestMapping("*")
    public String notFind() {
        return "404";
    }
}
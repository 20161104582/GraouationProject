package com.web.sys.pcontroller;

//import com.book.domain.Admin;
//import com.book.domain.ReaderCard;
//import com.book.service.LoginService;

import com.web.common.bean.DataRes;
import com.web.sys.bean.ProMatch;
import com.web.sys.bean.SysUserRole;
import com.web.sys.bean.ViewUserInfo;
import com.web.sys.service.*;
import com.web.sys.utils.R;
import com.web.sys.bean.SysUser;
import com.web.sys.utils.T;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//标注为一个Spring mvc的Controller
@Controller
public class SystemController {


    @Autowired
    SysUserService userService;

    @Autowired
    ProSchoolService schoolService;

    @Autowired
    SysUserRoleService user_roleService;
    //负责处理login.html请求
    @ApiOperation(notes="请求首页",value = "request index")
    @RequestMapping(value = {"/index"},method=RequestMethod.GET)
    public String toLogin(HttpServletRequest request, Model model){
        return "admin/index";
    }


    @RequestMapping(value = {"/reg"},method=RequestMethod.GET)
    public String reg(HttpServletRequest request, Model model){
        request.setAttribute("schools",schoolService.selectAll(null));
        return "admin/reg";
    }
    @Autowired
    ViewUserInfoService viewUserInfoService;


    @Autowired
    ProMatchService matchService;

    @Autowired
    ProSignService signService;

    @RequestMapping(value = {"/dataInfo"},method=RequestMethod.GET)
    @ResponseBody
    public Object dataInfo(HttpServletRequest request, Model model){
        ViewUserInfo viewUserInfo = new ViewUserInfo();

        Map<String,Object> ret = new HashMap<>();
        viewUserInfo.setRoleNum(4L);
        ret.put("stn",viewUserInfoService.queryByCondition(viewUserInfo).size());

        viewUserInfo.setRoleNum(3L);
        ret.put("ten",viewUserInfoService.queryByCondition(viewUserInfo).size());

        ret.put("pan",matchService.queryByCondition(null).size());

        ret.put("don",signService.queryByCondition(null).size());

        return DataRes.success(ret);
    }

    @RequestMapping(value = {"/login"},method=RequestMethod.GET)
    public String login(HttpServletRequest request, Model model){
        String msg = request.getParameter("m");
        model.addAttribute("msg",msg);
        SysUser user = (SysUser)request.getSession().getAttribute("user");

        return "admin/login";
    }

    @RequestMapping(value = {"/pg/{path}/{name}"},method=RequestMethod.GET)
    public String personal(HttpServletRequest request, Model model,@PathVariable String path,@PathVariable String name){
        return path + "/" + name;
    }

    //配置404页面
     @RequestMapping("*")
     public String notFind() {
         return "404";
     }
}
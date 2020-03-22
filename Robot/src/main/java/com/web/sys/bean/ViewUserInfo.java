package com.web.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.common.bean.Page;
import com.web.common.utils.DateUtils;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
* 
* @author wyb
*/
public class ViewUserInfo extends Page {
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String truename;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * {"name":"性别","0":"男","1":"女"}
     */
    private String sex;

    /**
     * 图片ID
     */
    private String photoid;

    /**
     * 出生日期
     */
    private Date brithday;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 最后登录时间
     */
    private Date lastlogin;

    /**
     * 用户类型
     */
    private Short itype;

    /**
     * 学校id
     */
    private Long schoolid;

    private Long userId;

    private Long roleId;

    private String roleName;

    private Long roleNum;

    private Long companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 公司电话
     */
    private String companyPhone;

    /**
     * 学校
     */
    private Long schoolId;

    /**
     * 名称
     */
    private String schoolName;

    /**
     * 地址
     */
    private String schoolAddress;

    private String companyIntro;

    /**
     * 简介
     */
    private String schoolIntro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid == null ? null : photoid.trim();
    }

    @JsonIgnore
    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    @JsonIgnore
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @JsonIgnore
    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Short getItype() {
        return itype;
    }

    public void setItype(Short itype) {
        this.itype = itype;
    }

    public Long getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Long schoolid) {
        this.schoolid = schoolid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Long getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(Long roleNum) {
        this.roleNum = roleNum;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress == null ? null : schoolAddress.trim();
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro == null ? null : companyIntro.trim();
    }

    public String getSchoolIntro() {
        return schoolIntro;
    }

    public void setSchoolIntro(String schoolIntro) {
        this.schoolIntro = schoolIntro == null ? null : schoolIntro.trim();
    }

    public String getSex_() {
        if(StringUtils.isEmpty(sex)){
             return "";
        }else if(sex.equals(0)){
             return "男";
        }else if(sex.equals(1)){
             return "女";
        }
        return "";
    }

    public String getBrithday_() {
        return DateUtils.formatDate(brithday);
    }

    public String getCreatetime_() {
        return DateUtils.formatDateTime(createtime);
    }

    public String getLastlogin_() {
        return DateUtils.formatDateTime(lastlogin);
    }

    public SysUser createUser() {
        SysUser user = new SysUser();
        user.setUsername(username) ;
        user.setTruename(truename);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAddress(address);
        user.setSex(sex);
        user.setBrithday(brithday);
        user.setCreatetime(createtime);
        user.setLastlogin(lastlogin);
        user.setItype(itype);
        user.setId(userId);
        return user;
    }


}
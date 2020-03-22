package com.web.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.common.bean.Page;
import com.web.common.utils.DateUtils;
import org.springframework.util.StringUtils;

/**
* 
* @author wyb
*/
public class ViewSchoolInfo extends Page {
    private String id;

    /**
     * 学校
     */
    private Long schoolId;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 主键ID
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String truename;

    /**
     * 简介
     */
    private String intro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}
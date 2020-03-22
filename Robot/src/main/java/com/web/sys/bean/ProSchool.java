package com.web.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.common.bean.Page;
import com.web.common.utils.DateUtils;
import org.springframework.util.StringUtils;

/**
* 学校
* @author wyb
*/
public class ProSchool extends Page {
    /**
     * 学校
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 简介
     */
    private String intro;

    public ProSchool(Long id){
        this.id = id;
    }
    public ProSchool(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}
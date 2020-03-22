package com.web.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.common.bean.Page;
import com.web.common.utils.DateUtils;
import java.util.Date;
import org.springframework.util.StringUtils;

/**
* 比赛ID
* @author wyb
*/
public class ProMatch extends Page {
    /**
     * 比赛ID
     */
    private Long id;

    /**
     * 比赛名称
     */
    private String name;

    /**
     * 发布时间
     */
    private Date pubtime;

    /**
     * 比赛时间
     */
    private Date time;

    private Long userId;

    /**
     * 比赛地点
     */
    private String address;

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

    @JsonIgnore
    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }

    @JsonIgnore
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPubtime_() {
        return DateUtils.formatDateTime(pubtime);
    }

    public String getTime_() {
        return DateUtils.formatDateTime(time);
    }
}
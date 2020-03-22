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
public class ViewMatchInfo extends Page {
    private String id;

    /**
     * 比赛ID
     */
    private Long matchId;

    /**
     * 比赛名称
     */
    private String matchName;

    /**
     * 发布时间
     */
    private Date matchPubtime;

    /**
     * 比赛时间
     */
    private Date matchTime;

    /**
     * 比赛地点
     */
    private String matchAddress;

    /**
     * 真实姓名
     */
    private String truename;

    /**
     * 电话
     */
    private String phone;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 公司ID
     */
    private Long companyId;

    private String companyIntro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName == null ? null : matchName.trim();
    }

    @JsonIgnore
    public Date getMatchPubtime() {
        return matchPubtime;
    }

    public void setMatchPubtime(Date matchPubtime) {
        this.matchPubtime = matchPubtime;
    }

    @JsonIgnore
    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchAddress() {
        return matchAddress;
    }

    public void setMatchAddress(String matchAddress) {
        this.matchAddress = matchAddress == null ? null : matchAddress.trim();
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro == null ? null : companyIntro.trim();
    }

    public String getMatchPubtime_() {
        return DateUtils.formatDateTime(matchPubtime);
    }

    public String getMatchTime_() {
        return DateUtils.formatDateTime(matchTime);
    }
}
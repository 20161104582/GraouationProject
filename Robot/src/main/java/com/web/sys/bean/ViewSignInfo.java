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
public class ViewSignInfo extends Page {
    private String id;

    private Long signId;

    private Long matchId;

    private Long teacherId;

    /**
     * 报名时间
     */
    private Date signTime;

    /**
     * 最终成绩
     */
    private Integer score;

    /**
     * 结果文件
     */
    private Long fileId;

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
    private String chargerName;

    /**
     * 真实姓名
     */
    private String teacherName;

    /**
     * 名称
     */
    private String schoolName;

    /**
     * 地址
     */
    private String schoolAddress;

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
     * 简介
     */
    private String schoolIntro;

    private String companyIntro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @JsonIgnore
    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
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

    public String getChargerName() {
        return chargerName;
    }

    public void setChargerName(String chargerName) {
        this.chargerName = chargerName == null ? null : chargerName.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
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

    public String getSchoolIntro() {
        return schoolIntro;
    }

    public void setSchoolIntro(String schoolIntro) {
        this.schoolIntro = schoolIntro == null ? null : schoolIntro.trim();
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro == null ? null : companyIntro.trim();
    }

    public String getSignTime_() {
        return DateUtils.formatDateTime(signTime);
    }

    public String getMatchPubtime_() {
        return DateUtils.formatDateTime(matchPubtime);
    }

    public String getMatchTime_() {
        return DateUtils.formatDateTime(matchTime);
    }
}
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
public class ProSign extends Page {
    private Long id;

    private Long matchId;

    private Long teacherId;

    /**
     * 报名时间
     */
    private Date signTime;

    /**
     * 结果文件
     */
    private Long fileId;

    /**
     * 最终成绩
     */
    private Integer score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSignTime_() {
        return DateUtils.formatDateTime(signTime);
    }
}
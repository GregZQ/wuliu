package com.web.domain;

import com.web.utils.TimeTransfer;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * 货物运输清单
 */
public class Sends {

    private Integer sid;

    private String carid;

    private Date loadTime;

    private String fromLine;

    private String toLine;

    private String sendTimeValue;

    private Date sendTime;

    private String savePath;

    private Integer uid;

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
        if (StringUtils.isEmpty(sendTimeValue)){
            this.sendTimeValue = TimeTransfer.dateToString(sendTime,"yyyy-MM-dd");
        }
    }

    public void setSendTimeValue(String sendTimeValue) {
        this.sendTimeValue = sendTimeValue;
        if (Objects.isNull(sendTimeValue)){
            try {
                this.sendTime = TimeTransfer.stringToDate(loadTimeValue,"yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Date getSendTime() {
        return sendTime;
    }

    public String getSendTimeValue() {
        return sendTimeValue;
    }

    public String getLoadTimeValue() {
        return loadTimeValue;
    }

    public void setLoadTimeValue(String loadTimeValue) {
        this.loadTimeValue = loadTimeValue;
        if (this.loadTime==null){
            try {
                this.loadTime= TimeTransfer.stringToDate(loadTimeValue,"yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private String loadTimeValue;


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public Date getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Date loadTime) {
        this.loadTime = loadTime;
        if (this.loadTimeValue==null||
                this.loadTimeValue.trim().equals("")){
            this.loadTimeValue=TimeTransfer.dateToString(loadTime,"yyyy-MM-dd");
        }
    }

    public String getFromLine() {
        return fromLine;
    }

    public String getToLine() {
        return toLine;
    }

    public void setFromLine(String fromLine) {
        this.fromLine = fromLine;
    }

    public void setToLine(String toLine) {
        this.toLine = toLine;
    }
}

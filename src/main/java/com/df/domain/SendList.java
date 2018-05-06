package com.df.domain;

import com.df.controller.utils.TimeTransfer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * 货物运输清单
 */
public class SendList{

    private Integer sid;

    private String carid;

    private Date loadTime;

    private String place;

    private BigDecimal moneysMe;

    private BigDecimal moneysHe;
    //卸货地址
    private String putPlace;

    private String linkPhone;

    private Integer totalPages;

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public BigDecimal getMoneysMe() {
        return moneysMe;
    }

    public void setMoneysMe(BigDecimal moneysMe) {
        this.moneysMe = moneysMe;
    }

    public BigDecimal getMoneysHe() {
        return moneysHe;
    }

    public void setMoneysHe(BigDecimal moneysHe) {
        this.moneysHe = moneysHe;
    }

    public String getPutPlace() {
        return putPlace;
    }

    public void setPutPlace(String putPlace) {
        this.putPlace = putPlace;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }
}

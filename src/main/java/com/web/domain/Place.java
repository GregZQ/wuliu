package com.web.domain;

/**
 * 位置信息
 */
public class Place {
    private Integer id;
    private String place;
    private Integer uid;

    public Integer getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

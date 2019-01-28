package com.df.domain;

/**
 * 发货卸货地
 */
public class Des {
    private Integer id;
    private String loadPlace;
    private Integer uid;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setLoadPlace(String loadPlace) {
        this.loadPlace = loadPlace;
    }

    public Integer getUid() {
        return uid;
    }

    public Integer getId() {
        return id;
    }

    public String getLoadPlace() {
        return loadPlace;
    }
}

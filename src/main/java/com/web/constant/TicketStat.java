package com.web.constant;

/**
 * Created by Jone on 2019-01-26.
 */
public enum  TicketStat {

    NOSTAT(-1,"无状态"),
    NOCHOSE(0,"未被选中"),
    CHOSE(1,"被选中");

    private Integer code;
    private String des;

    TicketStat(Integer code,String des){
        this.code=code;
        this.des=des;
    }

    public boolean match(Integer code){
        return this.code.equals(code);
    }

    public String getDes() {
        return des;
    }

    public Integer getCode() {
        return code;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

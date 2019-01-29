package com.web.constant;


/**
 * 票据状态
 */
public enum FileType {
    // @formatter:off
    TICKET(1,"开票文件"),
    SENDLIST(2,"运输文件");
    // @formatter:on

    private Integer code;
    private String des;

    FileType(Integer code,String des){
        this.code = code;
        this.des  = des;
    }

    public boolean match(Integer code){
        return this.code.equals(code);
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}

package com.df.domain;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户User
 */
public class User {
    private Integer id;

    private String username;

    private String password;

    private String company;

    private String ticket;

    private String fuben;

    private String end;

    public String getFuben() {
        return fuben;
    }

    public String getEnd() {
        return end;
    }

    public String getTicket() {
        return ticket;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setFuben(String fuben) {
        this.fuben = fuben;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getPassword() {
        return password;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static User newInstance(String username,String password,
                                   String company){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCompany(company);

        return user;
    }
}

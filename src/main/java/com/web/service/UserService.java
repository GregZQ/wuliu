package com.web.service;

import com.web.dao.UserDao;
import com.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User findUserById(Integer id){
       return this.userDao.findUserById(id);
    }

    /**
     * 插入用户
     * @param user
     */
    public void insert(User user){
        this.userDao.insert(user);
    }

    /**
     * 根据username查询用户
     * @param username
     * @return
     */
    public User findUserByUsername(String username) {
        return this.userDao.findUserByUsername(username);
    }

    /**
     * 更新用户存储文件路径
     * @param user
     */
    public void updatePath(User user) {
        this.userDao.updatePath(user);
    }

    /**
     * 更新用户公司名称
     * @param user
     */
    public void renameCompany(User user) {
        this.userDao.updateCompany(user);
    }
}

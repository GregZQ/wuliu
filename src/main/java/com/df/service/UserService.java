package com.df.service;

import com.df.dao.UserDao;
import com.df.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findUserById(Integer id){
       return this.userDao.findUserById(id);
    }

    public void insert(User user){
        this.userDao.insert(user);
    }

    public User findUserByUsername(String username) {
        return this.userDao.findUserByUsername(username);
    }

    public void updatePath(User user) {
        this.userDao.updatePath(user);
    }
}

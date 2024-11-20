package com.ssafy.ssafitlife.user.model.service;

import com.ssafy.ssafitlife.user.model.dao.UserDao;
import com.ssafy.ssafitlife.user.model.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUserList() {
        return userDao.selectAll();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.selectByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userDao.insertUser(user);
    }
}

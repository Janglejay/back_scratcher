package com.janglejay.service;

import com.janglejay.myEnum.UserEnum;
import com.janglejay.dao.UserDao;
import com.janglejay.entity.User;
import com.janglejay.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserDao userDao;

    public boolean authUser(User user) {
        log.info("user auth is {}", Objects.requireNonNull(UserEnum.getByAuthCode(user.getAuthCode())).getAuthDesc());
        return user.getAuthCode().equals(UserEnum.FORMAL.getAuthCode());
    }

    public List<User> queryAllUser() {
        List<User> users = userDao.selectAllUser();
        if (!users.isEmpty()) return users;
        return ListUtils.of(new User("not anyone"));
    }
}

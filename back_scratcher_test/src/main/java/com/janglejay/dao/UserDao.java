package com.janglejay.dao;

import com.janglejay.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {
    public List<User> selectAllUser() {
        User user = new User("user");
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        User user4 = new User("user4");
        return List.of(user, user1, user2, user3, user4);
    }
}

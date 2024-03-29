package com.benny.jane.controller;

import com.benny.jane.entity.User;
import com.benny.jane.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/all")
    List<User> getList() {
        List<User> users = userMapper.queryAll();
        for (User u : users) {
            logger.info(u.toString());
        }
        return users;
    }


    @RequestMapping("/query")
    User queryById(int id) {
        User user = userMapper.queryById(id);
        logger.info(user.toString());
        return user;
    }

    @RequestMapping("/add")
    String add(User user) {
        return userMapper.add(user) == 1 ? "success" : "failed";
    }

    @RequestMapping("/update")
    String updateById(User user) {
        return userMapper.updateById(user) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delete")
    String delById(int id) {
        return userMapper.delById(id) == 1 ? "success" : "failed";
    }

}

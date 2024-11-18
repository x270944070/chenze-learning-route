package com.chenze.sharding.learning.test;

import com.chenze.sharding.learning.mapper.UserMapper;
import com.chenze.sharding.learning.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadWriteTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setUname("张三丰");
        userMapper.insert(user);
    }

}

package cn.easysale.core.service;

import cn.easysale.core.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liaozhisong on 3/23/14.
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"classpath:spring-context.xml"})
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void register() {
        User user = new User();
        user.setName("测试用户");
        user.setUsername("admin");
        user.setPassword("admin");
        userService.addUser(user);
    }
}

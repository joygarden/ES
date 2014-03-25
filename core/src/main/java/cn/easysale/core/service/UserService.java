package cn.easysale.core.service;

import cn.easysale.core.entity.User;
import cn.easysale.core.support.BaseService;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.stereotype.Service;

/**
 * 用户管理类.
 */
@Service
public class UserService extends BaseService<User> {

    public void addUser(User user) {
        PasswordService passwordService = new DefaultPasswordService();
        user.setPassword(passwordService.encryptPassword(user.getPassword()));
        addObject(user);
    }


}

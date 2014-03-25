package cn.easysale.core.service;

import cn.easysale.core.entity.User;
import cn.easysale.core.support.BaseService;
import cn.easysale.core.support.ShiroDbRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理类.
 *
 */
@Service
public class UserService extends BaseService<User> {

    public void register(User user) {
        PasswordService passwordService = new DefaultPasswordService();
        user.setPassword(passwordService.encryptPassword(user.getPassword()));
        addObject(user);
    }


}

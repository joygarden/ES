/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.easysale.core.support;

import cn.easysale.core.entity.User;
import cn.easysale.core.service.UserService;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.io.Serializable;

@Component
public class ShiroDbRealm extends AuthorizingRealm {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserService userService;


    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        try {
            UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
            String userName = token.getUsername();
            String plainPassword = String.valueOf(token.getPassword());
            if (userName != null && !"".equals(userName)) {
                User user = userService.findUniqueBy("username", token.getUsername());
                if (user != null) {
                    PasswordService passwordService = new DefaultPasswordService();
                    if (passwordService.passwordsMatch(plainPassword, user.getPassword())) {
                        return new SimpleAuthenticationInfo(
                                user.getUsername(), plainPassword, getName());
                    }
                }

            }
        } catch (NoResultException e) {
            RuntimeException re = new RuntimeException("用户名不存在！", e);
            logger.error(re.getMessage(), re);
            throw re;
        } catch (NonUniqueResultException e) {
            RuntimeException re = new RuntimeException("存在重名用户！", e);
            logger.error(re.getMessage(), re);
            throw re;
        } catch (Exception e) {
            logger.error("", e);
        }

        throw new RuntimeException("密码错误！");
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        User user = userService.findUniqueBy("username", shiroUser.getUsername());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(user.getRoleList());
        return info;
    }


    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private static final long serialVersionUID = -1373760761780840081L;
        public Long id;
        public String username;
        public String name;
        public Long companyId;

        public ShiroUser(Long id, String username, String name, Long companyId) {
            this.id = id;
            this.username = username;
            this.name = name;
            this.companyId = companyId;
        }

        public String getName() {
            return name;
        }

        public String getUsername() {
            return username;
        }

        public Long getCompanyId() {
            return companyId;
        }

        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return username;
        }

        /**
         * 重载equals,只计算username;
         */
        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this, "username");
        }

        /**
         * 重载equals,只比较username
         */
        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj, "username");
        }
    }

}

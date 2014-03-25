package cn.easysale.core.entity;

import cn.easysale.core.support.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.List;

@Entity
public class User extends BaseEntity {
    private String username;
    private String name;
    private String password;
    private String roles;
    private Long companyId;
    private Long pushNum; //推送数
    private Long accessNum; //访问UV
    private Long accessPv;  //访问PV
    private Integer deleteFlag;
    private String avatarUrl;

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Transient
    @JsonIgnore
    public List<String> getRoleList() {
        return Arrays.asList(roles.split(","));
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getPushNum() {
        return pushNum;
    }

    public void setPushNum(Long pushNum) {
        this.pushNum = pushNum;
    }

    public Long getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(Long accessNum) {
        this.accessNum = accessNum;
    }

    public Long getAccessPv() {
        return accessPv;
    }

    public void setAccessPv(Long accessPv) {
        this.accessPv = accessPv;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
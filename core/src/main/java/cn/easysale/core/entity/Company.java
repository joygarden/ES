package cn.easysale.core.entity;

import cn.easysale.core.support.BaseEntity;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: binbin
 * Date: 13-2-17
 * Time: 下午10:27
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Company extends BaseEntity {
    private String name;
    private String telphone;
    private String telphone2;
    private String description; //描述
    private String logoFile;
    private String adImageUrl; //广告图片地址
    private String weixinImageUrl; //微信图片地址
    private String weixinDesc; //微信描述
    private String domain; //二级域名
    private String androidUrl; //andorid下载地址
    private String iphoneUrl; //iphone下载地址
    private Integer smsNum; //剩余短信数
    private Integer smsTotal; //短信总数
    private String style; //网站风格

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getTelphone2() {
        return telphone2;
    }

    public void setTelphone2(String telphone2) {
        this.telphone2 = telphone2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(String logoFile) {
        this.logoFile = logoFile;
    }

    public String getAdImageUrl() {
        return adImageUrl;
    }

    public void setAdImageUrl(String adImageUrl) {
        this.adImageUrl = adImageUrl;
    }

    public String getWeixinImageUrl() {
        return weixinImageUrl;
    }

    public void setWeixinImageUrl(String weixinImageUrl) {
        this.weixinImageUrl = weixinImageUrl;
    }

    public String getWeixinDesc() {
        return weixinDesc;
    }

    public void setWeixinDesc(String weixinDesc) {
        this.weixinDesc = weixinDesc;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getIphoneUrl() {
        return iphoneUrl;
    }

    public void setIphoneUrl(String iphoneUrl) {
        this.iphoneUrl = iphoneUrl;
    }

    public Integer getSmsNum() {
        return smsNum;
    }

    public void setSmsNum(Integer smsNum) {
        this.smsNum = smsNum;
    }

    public Integer getSmsTotal() {
        return smsTotal;
    }

    public void setSmsTotal(Integer smsTotal) {
        this.smsTotal = smsTotal;
    }

}

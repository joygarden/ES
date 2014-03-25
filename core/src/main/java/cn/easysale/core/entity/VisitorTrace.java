package cn.easysale.core.entity;

import cn.easysale.core.support.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: cwhl2201
 * Date: 13-5-18
 * Time: 下午7:04
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class VisitorTrace extends BaseEntity {
    private String visitUrl;
    private String staticsUrl;
    private Long companyId;
    private Member member;
    private String urlTitle;
    private String visitIp;
    private String visitArea;
    private String visitorName;
    private String isNew;
    private String level;
    private Long resourceId;
    private Long visitLength;
    private String province;
    private String area;
    private String userAgent;
    private String source;
    private BigDecimal longitude;//经度
    private BigDecimal latitude; //纬度


    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getVisitLength() {
        return visitLength;
    }

    public void setVisitLength(Long visitLength) {
        this.visitLength = visitLength;
    }

    public String getNew() {
        return isNew;
    }

    public void setNew(String aNew) {
        isNew = aNew;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    public String getStaticsUrl() {
        return staticsUrl;
    }

    public void setStaticsUrl(String staticsUrl) {
        this.staticsUrl = staticsUrl;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp;
    }

    public String getVisitArea() {
        return visitArea;
    }

    public void setVisitArea(String visitArea) {
        this.visitArea = visitArea;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "member_id")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

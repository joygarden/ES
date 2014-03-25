package cn.easysale.core.entity;

import cn.easysale.core.support.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by liaozhisong on 3/23/14.
 */
@Entity
public class Guest extends BaseEntity {
    private Long companyId;

    public Guest(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}

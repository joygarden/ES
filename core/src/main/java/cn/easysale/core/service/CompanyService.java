package cn.easysale.core.service;

import cn.easysale.core.entity.Company;
import cn.easysale.core.support.BaseService;
import cn.easysale.core.support.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaozhisong on 3/22/14.
 */
@Service
public class CompanyService extends BaseService<Company> {
    public Page<Company> findCompanyList(Company company,Page page) {
        String hql = "select name from Company where name like :name and style= :style and id in(:ids) order by id";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("name","%"+company.getName()+"%");
        params.put("style","a");
        params.put("ids", Arrays.asList(new Object[]{3l, 4l, 13l}));
        return pagedQuery(hql, page, params);
    }
}

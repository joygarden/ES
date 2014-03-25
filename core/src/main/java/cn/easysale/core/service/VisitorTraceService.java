package cn.easysale.core.service;

import cn.easysale.core.entity.VisitorTrace;
import cn.easysale.core.support.BaseService;
import cn.easysale.core.util.CityAlphaUtil;
import cn.easysale.core.util.geo.BaiduIp;
import cn.easysale.core.util.geo.IPLocator;
import cn.easysale.core.util.geo.geoip.Location;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by liaozhisong on 3/22/14.
 */
@Service
public class VisitorTraceService extends BaseService<VisitorTrace> {

    public void addVisitorTrace(VisitorTrace vt,Map paramMap) {
        try {
            BigDecimal[] location = BaiduIp.getLocation(vt.getVisitIp());
            if (location != null) {
                vt.setLatitude(location[0]);
                vt.setLongitude(location[1]);
            }
        } catch (Exception le) {
            logger.error("BaiduIp.getLoaction Exception !!!", le);
        }

        Location location = IPLocator.getLoaction(vt.getVisitIp());
        logger.info("STATICS IPLocator getLoaction=" + vt.getVisitIp());
        if (location != null && location.city != null) {
            vt.setVisitArea(location.city);
        } else {
            vt.setVisitArea("unknow");
        }
        CityAlphaUtil.findCity(vt.getVisitIp(), vt);
        logger.info("area=" + vt.getArea() + "--" + vt.getVisitArea() + " ip=" + vt.getVisitIp());

        if (paramMap != null) {
            String requestUrl = "";
            for(Object obj : paramMap.keySet()) {
                String key = String.valueOf(obj);
                String[] value = (String[]) paramMap.get(key);
                requestUrl += "&" + key + "=" + value[0];
            }
            requestUrl = StringUtils.isBlank(requestUrl)?
                    vt.getVisitUrl():vt.getVisitUrl()+"?"+requestUrl.substring(1);
            logger.info("STATICS  getUrlTitle=" + requestUrl);
        }



        addObject(vt);
    }
}

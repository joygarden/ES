package cn.easysale.core.util.geo;/* IPLocator.java */


import cn.easysale.core.support.Config;
import cn.easysale.core.util.geo.geoip.Location;
import cn.easysale.core.util.geo.geoip.LookupService;
import cn.easysale.core.util.geo.geoip.RegionName;
import cn.easysale.core.util.geo.geoip.TimeZone;
import org.apache.log4j.Logger;

import java.io.IOException;

/* sample of how to use the GeoIP Java API with GeoIP City database */
/* Usage: java IPLocator 64.4.4.4 */

public class IPLocator {
    private static Logger logger = Logger.getLogger(IPLocator.class);

    private static LookupService lookupService = null;

    static {
        try {
            lookupService = new LookupService(Config.GEO_IP_FILE, LookupService.GEOIP_MEMORY_CACHE);
        } catch (IOException e) {
            logger.error("LookupService Exception !!!", e);
        }
    }

    public static Location getLoaction(String sourceIp) {
        Location loc = lookupService.getLocation(sourceIp);
        lookupService.close();
        return loc;

    }


    public static void main(String[] args) {

        Location l1 = getLoaction("27.191.51.96");
        Location l2 = getLoaction("27.191.51.96");
        System.out.println("countryCode: " + l2.countryCode +
                "\n countryName: " + l2.countryName +
                "\n region: " + l2.region +
                "\n regionName: " + RegionName.regionNameByCode(l2.countryCode, l2.region) +
                "\n city: " + l2.city +
                "\n postalCode: " + l2.postalCode +
                "\n latitude: " + l2.latitude +
                "\n longitude: " + l2.longitude +
                "\n distance: " + l2.distance(l1) +
                "\n distance: " + l1.distance(l2) +
                "\n metro code: " + l2.metro_code +
                "\n area code: " + l2.area_code +
                "\n timezone: " + TimeZone.timeZoneByCountryAndRegion(l2.countryCode, l2.region));

        lookupService.close();

    }
}

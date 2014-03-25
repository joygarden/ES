package cn.easysale.core.util.geo;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: romkk
 * Date: 13-4-7
 * Time: 下午1:29
 * To change this template use File | Settings | File Templates.
 */
public class BaiduIp {

    private static String url = "http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip=${ip}";

    public static BigDecimal[] getLocation(String ip) throws Exception {
        BigDecimal[] ret = new BigDecimal[2];
        String ipUrl = StringUtils.replace(url, "${ip}", ip);
        try {
            String content = IOUtils.toString(new URL(ipUrl));
            if (StringUtils.isNotBlank(content)) {
                String lat = StringUtils.substringBetween(content, "\"y\":\"", "\"");
                String lng = StringUtils.substringBetween(content, "\"x\":\"", "\"");
                if (StringUtils.isNotBlank(lat) && StringUtils.isNotBlank(lng)) {
                    ret[0] = new BigDecimal(lat).divide(new BigDecimal(100000));
                    ret[1] = new BigDecimal(lng).divide(new BigDecimal(100000));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static void main(String[] args) throws Exception {
        String ip = "27.191.51.96";
        System.out.println(IOUtils.toString(new URL("http://www.qq.com")));
    }
}

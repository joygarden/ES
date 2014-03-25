package cn.easysale.core.util.geo;

import cn.easysale.core.util.CityAlphaUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: cwhl2201
 * Date: 13-6-22
 * Time: 下午12:24
 * To change this template use File | Settings | File Templates.
 */
public class IP138Locator {

    private static Logger logger = Logger.getLogger(IP138Locator.class);

    private static String url = "http://www.baidu.com/s?wd=${ip}";


    public static void main(String[] args) {
        String ip = "110.75.186.226";

        if (ip != null && ip.indexOf(",") > 0) {
            String[] ips = ip.split(",");
            ip = ips[ips.length - 1].trim();
        }
        IP138Locator util = new IP138Locator();
        String[] aaResult = util.parserIp("123.233.4.74");
        aaResult = util.parserIp("123.233.4.74");
        aaResult = util.parserIp("110.195.43.130");
        aaResult = util.parserIp("182.118.20.223");
        aaResult = util.parserIp("123.233.4.74");

    }

    /**
     * 根据url获取title，注意不要引起拦截器的再次拦截，形成环路
     * 没有取到title返回uri
     * 由于时间原因暂不考虑性能问题
     *
     * @return
     */
    public static String[] parserIp(String ip) {

        String[] info = null;

        try {
            String ipUrl = StringUtils.replace(url, "${ip}", ip);

            URL urlObj = new URL(ipUrl);
            String content = IOUtils.toString(urlObj, "UTF-8");
            info = getDescption(content);



        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取title失败", e);

        } finally {
            return info;
        }

    }


    /**
     * 获得标题
     *
     * @param s
     * @return
     */
    private static String[] getDescption(String s) {
        String regex;
        String title = "";
        List<String> list = new ArrayList<String>();
        regex = "<strong>.*?</strong>";
        Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
        Matcher ma = pa.matcher(s);
        while (ma.find()) {
            list.add(ma.group());
        }
        if (list.size() >= 2) {
            title = list.get(1);
            title = title.replace("<strong>", "");
            title = title.replace("</strong>", "");
        }

        String[] str = new String[3];
        if (title.split(" ").length > 1) {
            str[2] = title.split(" ")[1];
            String area = title.split(" ")[0];

            for (String aa : CityAlphaUtil.provences) {
                if (area.indexOf(aa) >= 0) {
                    str[0] = aa;
                    str[1] = area.replace(aa, "");

                }
            }
        } else {
            str[0] = "-1";
            str[1] = title.trim();
        }

        if("-1".equals(str[0])) {
       for (String aa : CityAlphaUtil.provences) {
                if (str[1].indexOf(aa) >= 0) {
                    str[0] = aa;
                    str[1] = str[1].replace(aa, "").trim();
                    break;
                }
            }
        }
        return str;
    }
}

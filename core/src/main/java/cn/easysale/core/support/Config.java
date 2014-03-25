package cn.easysale.core.support;

import cn.easysale.core.util.PropertyReader;

/**
 * Created by liaozhisong on 3/23/14.
 */
public class Config {

    private static final PropertyReader reader = new PropertyReader("/config.properties");

    public static final String GEO_IP_FILE = reader.getProperty("GEO_IP_FILE");

    public static final String MOBILE_URL_PREFIX = reader.getProperty("MOBILE_URL_PREFIX");

    public static final String UPLOAD_PATH = reader.getProperty("UPLOAD_PATH");
}

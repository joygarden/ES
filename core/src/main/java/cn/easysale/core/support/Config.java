package cn.easysale.core.support;

import cn.easysale.core.util.PropertyReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liaozhisong on 3/23/14.
 */
public class Config {

    private static final PropertyReader reader = new PropertyReader("/config.properties");

    public static final String GEO_IP_FILE = reader.getProperty("GEO_IP_FILE");

    public static final String MOBILE_URL_PREFIX = reader.getProperty("MOBILE_URL_PREFIX");
}

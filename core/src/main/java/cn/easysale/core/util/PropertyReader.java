package cn.easysale.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by liaozhisong on 3/23/14.
 */
public class PropertyReader {

    private Map<String,String> props = new HashMap<String, String>();
    public PropertyReader(String file) {
        props = new HashMap<String, String>();
       // "/config.properties"
        Properties prop = new Properties();
        InputStream in = Object.class.getResourceAsStream(file);
        try {
            prop.load(in);
            for(Object key :prop.keySet()) {
                Object value= prop.get(key);
                props.put(String.valueOf(key),String.valueOf(value));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getProperty(String key) {
        return props.get(key);
    }
}

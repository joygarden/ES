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
        Properties prop = new Properties();
        InputStream in = null;
        try {
           // in= Object.class.getResourceAsStream(file);
            //in = new ClassPathResource(file).getInputStream();
            ClassLoader cl = null;
            try {
                cl = Thread.currentThread().getContextClassLoader();
            } catch (Throwable ex) {
                // Cannot access thread context ClassLoader - falling back to system class loader...
            }
            if (cl == null) {
                // No thread context class loader -> use class loader of this class.
                cl = PropertyReader.class.getClassLoader();
            }
            in = cl.getResourceAsStream(file);
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

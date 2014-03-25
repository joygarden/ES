package cn.easysale.web.mobile.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liaozhisong on 3/22/14.
 */
@RestController
@RequestMapping("/rest/main")
public class MainRestAction {
    @RequestMapping("/index")
    public Map<String, String> index() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aa", "bb");
        map.put("cc", "dd");
        return map;
    }
}

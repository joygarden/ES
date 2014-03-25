package cn.easysale;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liaozhisong on 3/22/14.
 */
public class PostTest {


    public static String getTest() throws IOException {
        Content content =  Request.Get("http://www.baidu.com")
                .execute().returnContent();
        return  content.asString();
    }

    public static String postTest() throws IOException {
        Content content = Request.Post("http://targethost/login")
                .bodyForm(Form.form().add("username",  "vip").add("password",  "secret").build())
                .execute().returnContent();
        return  content.asString();
    }

    private static Long getCompanyIdbyUri(String uri) {
        Long companyId = null;
        Pattern p2 = Pattern.compile("/[0-9]+");
        Matcher m2 = p2.matcher(uri);
        while (m2.find()) {
            String str = m2.group().replace("/", "");
            companyId = Long.parseLong(str);
        }
        return companyId;
    }

    public static void main(String[] args) {
        String uri = "/1018/share/dwdwwd1";
        System.out.println(getCompanyIdbyUri(uri));
        String str = new String(Base64.encodeBase64("非法入侵".getBytes()));
        System.out.println(str);
        System.out.println(new String(Base64.decodeBase64(str)));
    }
}

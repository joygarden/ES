package cn.easysale.web.mobile.interceptor;

import cn.easysale.core.entity.Guest;
import cn.easysale.core.entity.Member;
import cn.easysale.core.entity.VisitorTrace;
import cn.easysale.core.service.MemberService;
import cn.easysale.core.service.VisitorTraceService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liaozhisong on 3/22/14.
 */
public class StatisticsInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String MEMBER_KEY = "MEMBER_UKEY";
    public static final String GUEST_UKEY = "GUEST_UKEY";

    @Autowired
    private MemberService memberService;

    @Autowired
    private VisitorTraceService visitorTraceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        request.setAttribute("basePath",basePath);

        String requestUrl = request.getRequestURL().toString();
        String requestUri = request.getRequestURI();
        String remoteIp = getRealIP(request);
        String userAgent = request.getHeader("User-Agent");
        String source = request.getParameter("source");
        if(!("sina".equals(source)||"tencent".equals(source)
                ||"renren".equals(source)||"douban".equals(source)
                ||"douban".equals(source)||"qzone".equals(source)
                ||"qzone".equals(source)||"wxsession".equals(source)
                ||"wxsession".equals(source)||"email".equals(source))) {
            source = "";
        }
        if(isKeyUrl(requestUrl)) {
            String clientUA = request.getParameter("clientUA");
            String memberId = request.getParameter("memberId");
            String userId = request.getParameter("userId");
            String selfFlag = request.getParameter("selfFlag");

            VisitorTrace vt = new VisitorTrace();
            vt.setCompanyId(getCompanyIdbyUri(requestUri));
            vt.setVisitUrl(requestUrl);
            vt.setStaticsUrl(requestUri);
            vt.setVisitIp(remoteIp);
            vt.setCreateDate(new Date());
            vt.setUserAgent(userAgent);
            vt.setSource(source);

            Cookie[] cookies = request.getCookies();
            String memberCookie = null;
            String guestCookie = null;
            if (cookies != null && cookies.length > 0) {
                for (Cookie coo : cookies) {
                    String cookieName = coo.getName();
                    String cookieValue = coo.getValue();
                    logger.info("STATICS cookieName=" + cookieName + " cookieValue=" + cookieValue);
                    if(MEMBER_KEY.equals(cookieName)) {
                        memberCookie = cookieValue;
                    }
                    if(GUEST_UKEY.equals(cookieName)) {
                        guestCookie = cookieValue;
                    }
                }
            } else {
                logger.info("STATICS COOKIE is null");
            }
            Long companyId = vt.getCompanyId();

            if(StringUtils.isNotBlank(memberCookie)) {
                String memberIdStr = new String(Base64.decodeBase64(memberCookie));
                Member member = memberService.findById(Long.parseLong(memberIdStr));
                if (member != null && member.getCompanyId().intValue() == companyId.intValue()) {
                    logger.info("STATICS 注册用户访问 " + memberIdStr + " companyId=" + companyId);
                    vt.setMember(member);
                    vt.setVisitorName(member.getName());
                    vt.setNew("N");
                } else {
                    logger.info("STATICS 注册用户访问 但非本项目客户" + memberIdStr + " companyId=" + companyId);
                }
            } else if(StringUtils.isNotBlank(memberId)) {
                Member member = memberService.findById(Long.parseLong(memberId));
                if (member != null && member.getCompanyId().intValue() == companyId.intValue()) {
                    //生成member cookie
                    response.addCookie(genCookie(MEMBER_KEY,memberId));
                    //写日志
                    logger.info("STATICS 注册用户第一次访问 " + " companyId=" + companyId);
                    vt.setMember(member);
                    vt.setVisitorName(member.getName());
                    vt.setNew("Y");
                } else {
                    logger.info("STATICS 注册用户访问 但非本项目客户" + memberId + " companyId=" + companyId);
                }
            } else if(StringUtils.isNotBlank(guestCookie)) {
                //有 写日志
                String guestName = new String(Base64.decodeBase64(guestCookie));
                logger.info("STATICS 老游客访问 " + guestName);
                vt.setVisitorName(guestName);
                vt.setNew("N");
            } else {
                // 生产 guest cookie
                Guest guest = new Guest(companyId);
                memberService.addObject(guest);
                String guestName = "游客" + guest.getId().toString();
                //写cookie
                response.addCookie(genCookie(GUEST_UKEY,guestName));
                // 记录日志
                logger.info("STATICS 游客第一次访问 " + guestName);
                vt.setVisitorName(guestName);
                vt.setNew("Y");
            }
            visitorTraceService.addVisitorTrace(vt,request.getParameterMap());
        }
        return super.preHandle(request, response, handler);
    }

    private Cookie genCookie(String type,String name) {
        String code = Base64.encodeBase64String(name.getBytes());
        Cookie cookie = new Cookie(type, code);
        cookie.setMaxAge(100 * 86400);
        cookie.setPath("/");
        return cookie;
    }

    private String getRealIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        logger.info("GET REAL IP : " + ip);
        if (ip != null && ip.indexOf(",") > 0) {
            String[] ips = ip.split(",");
            ip = ips[ips.length - 1].trim();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
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

    //以/结尾 非css。js。ico
    private boolean isKeyUrl(String url) {
        boolean flag = true;
        if (url.endsWith(".js") || url.endsWith(".css") || url.endsWith(".ico") || url.endsWith(".jpg") || url.endsWith(".png") || url.contains("/api/")) {
            flag = false;
        }
        return flag;
    }


}

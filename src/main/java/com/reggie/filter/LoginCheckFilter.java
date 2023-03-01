package com.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.reggie.config.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Luffy5522
 * @date: 2023/2/28 16:53
 * @description: 实现登录过滤器
 */
// url 拦截路径
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    // 路径匹配器,支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();

        // 1.获取本次请求的URI

        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        // 2、判断本次请求是否需要处理
        if (check(urls, url)) {
            //如果不需要处理，则直接放行
            log.info("路径:{}不需要拦截", url);
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }


        // 4、判断登录状态，如果已登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("employee") != null) {
            log.info("路径:{}不需要拦截", url);
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //  5、如果未登录则返回未登录结果

        httpServletResponse.getWriter().
                write(JSON.toJSONString(Result.error("NOTLOGIN")));

        //log.info("拦截到路径请求:{}", url);

    }

    /**
     * @param urls:
     * @param requestURL:
     * @return boolean
     * @author Luffy5522
     * @description 路径匹配, 检查是否需要放行
     * @date 2023/2/28 17:50
     */
    public boolean check(String[] urls, String requestURL) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURL);
            if (match)
                return true;

        }
        return false;
    }


}

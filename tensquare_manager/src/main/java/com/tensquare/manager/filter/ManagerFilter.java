package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author newHeart
 * @Create 2020/3/6 14:07
 */
@Component
public class ManagerFilter extends ZuulFilter{

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        // 登录不需要判断
        if (request.getRequestURL().toString().indexOf("login") > 0) {
            return null;
        }

        String header = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(header)){
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String role = (String) claims.get("roles");
                    if (role.equals("admin")) {
                        // 把头信息转发下去， 并且放行
                        context.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    context.setSendZuulResponse(false);  // 终止运行
                }
            }
        }
        context.setSendZuulResponse(false);      // 终止运行
        context.setResponseStatusCode(403);      // 权限不足
        context.setResponseBody("权限不足");
        context.getResponse().setContentType("text/html;charset=utf-8");
        System.out.println("网关过滤器");
        return null;
    }
}

package com.alvin.mall.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alvin.mall.common.api.ResultCode;
import com.alvin.mall.common.exception.ApiException;
import com.alvin.mall.common.util.ComConstants;
import com.alvin.mall.common.util.JwtTokenUtil;
import com.alvin.mall.modules.ums.model.UmsMember;
import com.alvin.mall.modules.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 作用：验证 用户是否登录、菜单资源权限
 * 作者：Alvin
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UmsMemberService umsMemberService;

    // 配置文件中的白名单secure.ignored.urls
    private List<String> urls;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    public JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、不需要登录就可以访问的路径——白名单
        String requestURI = request.getRequestURI();
        // Ant方式路径匹配 /**  ？  _
        PathMatcher matcher = new AntPathMatcher();
        for (String ignoredUrl : urls) {
            if(matcher.match(ignoredUrl,requestURI)){
                return  true;
            }
        }

        // 从请求头中获取 jwt token
        String jwtToken = request.getHeader(tokenHeader); // "Authorization"
        // 如果 token 为空，或者 token 不是以指定字符串开头，认定为未登录
        if (StrUtil.isBlank(jwtToken) || !jwtToken.startsWith(tokenHead)) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }

        // jwt token 解密
        jwtToken = jwtToken.substring(tokenHead.length()+1);
        String username = jwtTokenUtil.getUserNameFromToken(jwtToken);

        // 从服务器中获取当前用户
        UmsMember umsMember = umsMemberService.getAdminByUsername(username);
        if (null == umsMember) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        // 将当前用户名存储在ThreadLocal中
        jwtTokenUtil.currentUserName.set(username.trim());
        return true;
    }


    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}

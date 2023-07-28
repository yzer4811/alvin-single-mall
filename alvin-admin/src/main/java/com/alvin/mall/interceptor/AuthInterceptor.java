package com.alvin.mall.interceptor;

import com.alvin.mall.common.api.ResultCode;
import com.alvin.mall.common.exception.ApiException;
import com.alvin.mall.common.util.ComConstants;
import com.alvin.mall.modules.ums.model.UmsAdmin;
import com.alvin.mall.modules.ums.model.UmsResource;
import com.alvin.mall.modules.ums.service.UmsAdminService;
import com.alvin.mall.utils.MySession;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 配置文件中的白名单secure.ignored.urls
    private List<String> urls;

    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    public MySession mySession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、不需要登录就可以访问的路径——白名单
        // 获取当前请求   /admin/login
        String requestURI = request.getRequestURI();
        // Ant方式路径匹配 /**  ？  _
        PathMatcher matcher = new AntPathMatcher();
        for (String ignoredUrl : urls) {
            if(matcher.match(ignoredUrl,requestURI)){
                return  true;
            }
        }

//        String sessionId = request.getHeader("Authorization");
//        UmsAdmin umsAdmin = mySession.getCookie().get(sessionId);
//        2、未登录用户，直接拒绝访问
        if (null == request.getSession().getAttribute(ComConstants.FLAG_CURRENT_USER)) {
//        if (null == umsAdmin) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        } else {
            //3、已登录用户，判断是否有资源访问权限  Todo:到时候用spring security实现
            UmsAdmin umsAdmin = (UmsAdmin) request.getSession().getAttribute(ComConstants.FLAG_CURRENT_USER);
            // 获取用户所有可访问资源
            List<UmsResource> resourceList = umsAdminService.getResourceList(umsAdmin.getId());
            for (UmsResource umsResource : resourceList) {
                if(matcher.match( umsResource.getUrl(),requestURI)){
                    return  true;
                }
            }
            System.out.println(requestURI);
            throw new ApiException(ResultCode.FORBIDDEN);
        }
    }


    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}

package com.alvin.mall.modules.ums.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.alvin.mall.common.exception.ApiException;
import com.alvin.mall.common.exception.Asserts;
import com.alvin.mall.common.util.ComConstants;
import com.alvin.mall.common.util.JwtTokenUtil;
import com.alvin.mall.dto.MemberParamDTO;
import com.alvin.mall.modules.ums.mapper.*;
import com.alvin.mall.modules.ums.model.UmsMember;
import com.alvin.mall.modules.ums.model.UmsMemberLoginLog;
import com.alvin.mall.modules.ums.service.UmsMemberCacheService;
import com.alvin.mall.modules.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-12
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    private UmsMemberLoginLogMapper loginLogMapper;
    @Autowired
    private UmsMemberCacheService memberCacheService;
    @Autowired
    private HttpSession session;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UmsMember register(MemberParamDTO umsAdminParam) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsAdminParam, umsMember);
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);

        //查询是否有相同用户名的用户
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember::getUsername,umsMember.getUsername());
        List<UmsMember> umsMemberList = list(wrapper);

        // 大于 0 说明该用户已经被注册
        if (umsMemberList.size() > 0) {
            return null;
        }

        //将密码进行加密操作
        String encodePassword = BCrypt.hashpw(umsMember.getPassword());
        umsMember.setPassword(encodePassword);
        baseMapper.insert(umsMember);
        return umsMember;
    }

    @Override
    public UmsMember login(String username, String password) {

        //密码需要客户端加密后传递
        UmsMember umsMember=null;
        try {
            umsMember = loadUserByUsername(username);
            if(!BCrypt.checkpw(password,umsMember.getPassword())){
                Asserts.fail("密码不正确");
            }
            /*if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }*/
            insertLoginLog(username);
        } catch (Exception e) {
            Asserts.fail("登录异常:"+e.getMessage());
        }
        return umsMember;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsMember member = getAdminByUsername(username);
        if(member==null) return;
        UmsMemberLoginLog loginLog = new UmsMemberLoginLog();
        loginLog.setMemberId(member.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    @Override
    public UmsMember loadUserByUsername(String username){
        //获取用户信息
        UmsMember member = getAdminByUsername(username);
        if (member != null) {
            return member;
        }
        throw new ApiException("用户不存在");
    }

    @Override
    public UmsMember getAdminByUsername(String username) {
        // 先从redis缓存里取，取不到在从数据库中取
        UmsMember member = memberCacheService.getUser(username);
        if(member != null) return  member;

        // 从数据库中取用户数据
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMember::getUsername,username);
        List<UmsMember> memberList = this.list(wrapper);
        if (memberList != null && memberList.size() > 0) {
            member = memberList.get(0);
            // 从数据库中查询到数据，放到缓存中去，避免反复查询数据库
            memberCacheService.setUser(member);
            return member;
        }
        return null;
    }

    /**
     * 获得当前用户
     * @return
     */
    public UmsMember getCurrentMember(){

        String username = jwtTokenUtil.currentUserName.get();
        if (username != null) {
            return this.getAdminByUsername(username);
        }

        // 不从 session 中获取用户
        // return (UmsMember)session.getAttribute(ComConstants.FLAG_CURRENT_MEMBER);
        return null;
    }

}

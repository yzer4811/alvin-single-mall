package com.alvin.mall.controller;

import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.common.util.ComConstants;
import com.alvin.mall.common.util.JwtTokenUtil;
import com.alvin.mall.dto.MemberLoginParam;
import com.alvin.mall.dto.MemberParamDTO;
import com.alvin.mall.modules.ums.model.UmsMember;
import com.alvin.mall.modules.ums.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@Api(tags = "UserController", description = "前台用户服务接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    HttpSession session;
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    public JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;


    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsMember> register(@Validated @RequestBody MemberParamDTO umsAdminParam) {
        UmsMember umsMember = memberService.register(umsAdminParam);
        if (umsMember == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsMember);
    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated MemberLoginParam memberLoginParam) {
//        System.out.println(memberLoginParam);
        UmsMember login = memberService.login(memberLoginParam.getUsername(), memberLoginParam.getPassword());
        if (login == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        session.setAttribute(ComConstants.FLAG_CURRENT_MEMBER,login);

        // jwt 生成 token
        String token = jwtTokenUtil.generateUserNameStr(login.getUsername());
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("tokenHeader", tokenHeader);
        return CommonResult.success(tokenMap); // 返回jwt相关数据
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        session.setAttribute(ComConstants.FLAG_CURRENT_MEMBER,null);
        return CommonResult.success(null);
    }

}

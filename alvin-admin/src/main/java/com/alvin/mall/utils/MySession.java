package com.alvin.mall.utils;

import com.alvin.mall.modules.ums.model.UmsAdmin;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者 Alvin
 */
@Component
public class MySession {

    private static final Map<String, UmsAdmin> cookie = new HashMap<>();

    public void setCookie(String sessionId, UmsAdmin umsAdmin) {
        cookie.put(sessionId, umsAdmin);
    }

    public Map<String, UmsAdmin> getCookie() {
        return cookie;
    }

    @Override
    public String toString() {
        return "MySession{}";
    }



}

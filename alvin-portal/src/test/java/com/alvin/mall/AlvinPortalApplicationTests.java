package com.alvin.mall;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.BCrypt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class AlvinPortalApplicationTests {

	@Test
	void contextLoads() {
		String encodePassword = BCrypt.hashpw("123456");
		System.out.println(encodePassword);
		String encode = SecureUtil.md5("alvin商城项目密钥");
		System.out.println(encode);
	}

}

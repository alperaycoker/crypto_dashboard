package com.example.crypto_forex_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Bu sınıf, Spring Boot uygulamasının temel bağlamının (context)
 * hatasız bir şekilde yüklenip yüklenemediğini kontrol eden basit bir test içerir.
 */
@SpringBootTest(classes = CryptoForexApiApplication.class)
class CryptoForexApiApplicationTests {

	@Test
	void contextLoads() {
		// Bu test, uygulama bağlamı başarıyla yüklenirse geçer.
	}

}

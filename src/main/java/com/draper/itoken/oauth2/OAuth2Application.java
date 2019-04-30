package com.draper.itoken.oauth2;

import com.draper.itoken.oauth2.util.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author draper_hxy
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.draper.itoken.oauth2.mapper")
public class OAuth2Application {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(OAuth2Application.class, args);
		Thread.sleep(20 * 1000);
		for (String s : SpringContextUtil.getAllBeanNames()) {
			System.out.println(s);
		}
	}

}

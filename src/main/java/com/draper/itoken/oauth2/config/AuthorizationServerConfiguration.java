package com.draper.itoken.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 配置关于 OAuth 的存储
 *
 * @author draper_hxy
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration
		extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	/** 存储 token， db 为 oauth_access_token */
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	/** 存储用户信息 */
	@Bean
	public ClientDetailsService jdbcClientDetails() {
		return new JdbcClientDetailsService(dataSource);
	}


	/**
	 * 基于 oAuth2 授权码标准实现，做最安全的授权方式
	 * <url>https://tools.ietf.org/html/rfc6749#section-1.3.1</url>
	 *
	 * 定义客户细节服务的配合，可以初始化客户端细节，也可以引用现有的存储。
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// clients.inMemory().withClient("client").secret(passwordEncoder.encode("secret"))
		// .authorizedGrantTypes("authorization_code").scopes("app")
		// .redirectUris("https://github.com/itokenstudio");
		clients.withClientDetails(jdbcClientDetails());
	}

	/**
	 * 定义了授权。令牌切点和令牌服务。
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {

		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
		endpoints.tokenStore(tokenStore()); // 存储 令牌
		endpoints.userDetailsService(userDetailsService); // 用于刷新 token
		endpoints.authenticationManager(authenticationManager());
	}

	/**
	 * 定义了 token 切点的安全限制
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security)
			throws Exception {
		security.allowFormAuthenticationForClients();
		security.checkTokenAccess("permitAll()");
	}

	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
		daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}

	/**
	 * 在配置多种认证环境中需要多个 manager
	 * {@link AuthorizationServerConfigurer#configure(org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer)}
	 */
	@Bean
	AuthenticationManager authenticationManager() {
		return authentication -> daoAuthenticationProvider().authenticate(authentication);
	}

}

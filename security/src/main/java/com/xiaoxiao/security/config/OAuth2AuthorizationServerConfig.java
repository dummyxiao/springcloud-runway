package com.xiaoxiao.security.config;

import com.xiaoxiao.security.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 密码模式交互流程
 * 1.前端使用POST方式访问 /oauth/token获取token
 * 具体实现为,在header中添加属性 key = "Authorization" , value ="Basic base64加密(clientId:clientSecret)"
 * 在postman等工具中可以选择basic的认证方式
 * body中的必填参数有:
 * grant_type：表示授权类型，此处的值固定为"password"，必填项。
 * username：表示用户名，必填项。
 * password：表示用户的密码，必填项。
 * 2.返回token后，客户端将header中的Authorization属性的value修改为"bearer ${token}",就可以访问对应的资源
 *
 * authorization_code模式交互流程
 * 主要分为两步认证，第一步是对用户身份的认证，以及授权。
 * 第二步是对客户端身份的认证
 * 1. 获取授权码
 *浏览器请求：
 * /oauth/authorize?client_id=clientId
 * &redirect_uri=${callbackUrl}&response_type=code&scope=scope1
 * 认证服务器默认返回/login定义下的页面，用户登录验证了用户身份后，可以选择是否授权给客户端
 * 当用户选择授权的scope以后，认证服务器返回对应的code
 * 响应案例
 * https://github.com/?code=GYjbCj
 *2. 获取访问令牌token
 * 客户端根据返回的code去认证服务器获取token，
 *  /oauth/token POST
 *  必须参数
 *  header:  Authorization="Basic base64加密(clientId:clientSecret)"
 *  body:
 *  grant_type=authorization_code
 *  redirect_uri=${redirect_uri}
 *  code=${code}
 *  注意code只能使用一次
 *3.调用API
 * header中添加键值对key="Authorization" , value="bearer ${token}"
 *4.刷新token
 *  /oauth/token POST
 *  必须参数
 *  header:  Authorization="Basic base64加密(clientId:clientSecret)"
 *  body:
 *  grant_type=refresh_token
 *  refresh_token=${refresh_token}
 *
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/15
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JpaUserDetailsService userDetailsService;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordEncoder);//密码模式必须
    }

    /**
        配置客户端信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //todo 客户端多的话可以持久化到数据库

        clients.inMemory()
                .withClient("clientId")
                .secret("clientSecret")
                .authorizedGrantTypes("password","authorization_code","client_credentials","implicit","refresh_token")
                .scopes("scope1","scope2")
                .redirectUris("https://github.com/")
                ;
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authorizationCodeServices(authorizationCodeServices)//授权码模式
                .authenticationManager(authenticationManager)//密码模式
                .userDetailsService(userDetailsService);
    }
}

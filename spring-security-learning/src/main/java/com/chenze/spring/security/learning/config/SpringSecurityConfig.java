package com.chenze.spring.security.learning.config;

import com.chenze.spring.security.learning.handle.MyAccessDeniedHandler;
import com.chenze.spring.security.learning.handle.MyAuthenticationFailureHandler;
import com.chenze.spring.security.learning.handle.MyAuthenticationSuccessHandler;
import com.chenze.spring.security.learning.handle.MyLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAnyRole;

/**
 * SpringSecurity配置类
 * @author chenze
 * @date 2024/8/23 22:14
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private DataSource dataSource;
    @Resource
    private PersistentTokenRepository persistentTokenRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                // 当发现/login时，认为是登录（执行框架内部登录逻辑），必须和表单提交的地址一样，去执行UserDetailsService
                .loginProcessingUrl("/login")
                // 登录页面表单中，用户名的表单name。默认`username`
                .usernameParameter("username1")
                // 登录页面表单中，密码的表单name。默认`password`
                .passwordParameter("password1")
                // 登录成功之后跳转到页面，必须post请求
                .successForwardUrl("/toMain")
                // 登录成功后的处理器，不能和successForwardUrl共存
//                .successHandler(new MyAuthenticationSuccessHandler("http://www.baidu.com"))
                // 登录失败之后跳转到页面，必须post请求
                .failureForwardUrl("/toError")
                // 登录失败后的处理器，不能和failureForwardUrl共存
//                .failureHandler(new MyAuthenticationFailureHandler("https://www.hao123.com/"))
                // 自定义登录页面
//                .loginPage("/login.html")
                .loginPage("/showLogin")

        ;

        // 授权认证
        http.authorizeRequests()
                // login.html不需要被认证，并且限定请求方式为GET
//                .antMatchers(HttpMethod.GET, "/login.html").permitAll()
                .antMatchers(HttpMethod.GET, "/showLogin").permitAll()
                // error.html登录失败不需要认证
                .antMatchers("/error.html").permitAll()
                // 使用匹配符：**表示所有的文件（含子文件夹）
//                .antMatchers("/js/**", "/css/**", "/images/**").permitAll()
                // 正则表达式匹配访问
//                .regexMatchers(".+[.]png").permitAll()
                // 指定某些页面只有具有某些权限的账户才可以访问
                .antMatchers("/main1.html").hasAnyAuthority("admin")
                // 指定某些页面只有具有某些角色的账户才可以访问
//                .antMatchers("/main2.html").hasAnyRole("admin")
                // 指定某些页面只有具有ip地址才可以访问
                .antMatchers("/main3.html").hasIpAddress("127.0.0.1")

                /**
                 * 上述访问控制本质上都是通过方法{@link ExpressionUrlAuthorizationConfigurer.AuthorizedUrl.access()} 来控制的
                 * 下列案例相当于上述的：.antMatchers("/main2.html").hasAnyRole("admin")
                 */
//                .antMatchers("/main2.html").access("hasAnyRole('admin')")
                // 所有请求都必须被认证，必须登陆之后被访问
                .anyRequest().authenticated();
                // 自定义权限控制器
//                .anyRequest().access("@myUserPermissionControllerServiceImpl.hasPermission(request, authentication)");

        // 关闭csrf防护(跨域请求)
//        http.csrf().disable();

        // 异常处理
        http.exceptionHandling()
                // 访问权限不足
                .accessDeniedHandler(myAccessDeniedHandler);

        // 记住我
        http.rememberMe()
                // 设置失效时间
                .tokenValiditySeconds(60)
                // 自定义表单name
//                .rememberMeParameter()
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository);

        // 退出登录
        http.logout()
                // 退出登录的URL
                .logoutUrl("/logout")
                .logoutSuccessUrl("/showLogin?logout").permitAll()
//                .logoutSuccessHandler(new MyLogoutSuccessHandler("/showLogin"))
        ;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动建表，第一次启动时候需要，第二次启动注释掉
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

}

package org.handmade.miniproject.member.config;

import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.security.CustomAccessDeniedHandler;
import org.handmade.miniproject.member.security.filter.ApiCheckFilter;
import org.handmade.miniproject.member.security.filter.ApiLoginFilter;
import org.handmade.miniproject.member.security.filter.ApiRefreshFilter;
import org.handmade.miniproject.member.security.handler.LoginFailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("configure.......................");

        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable(); //csrf 토큰 기능 해제
        http.addFilterBefore(apiRefreshFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public ApiRefreshFilter apiRefreshFilter() {
        return new ApiRefreshFilter("/refresh");
    }

    @Bean
    public ApiCheckFilter apiCheckFilter(){
        return new ApiCheckFilter("/seller/**"); //체크 필터 적용 x중 수정사항
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {

        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login", authenticationManager());
        apiLoginFilter.setAuthenticationFailureHandler(new LoginFailHandler());

        return apiLoginFilter;
    }


}

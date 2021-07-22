
package org.handmade.miniproject.member.config;


import javax.sql.DataSource;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/**").permitAll()
                //.antMatchers("/login**", "/web-resources/**", "/products/**,/qnas/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/seller/**").hasAnyRole("ADMIN","SELLER")
                .antMatchers("/order/**").hasAnyRole("ADMIN","SELLER","CUSTOMER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                //.loginPage("/signin") //로그인페이지
                .defaultSuccessUrl("/main",true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/main")
                .permitAll();
                /*.and()
                .exceptionHandling()
                .accessDeniedPage("/");*/

                /*.authorizeRequests()
                //로그인 인증 없어도 접근 가능한 영역
                .antMatchers("/").permitAll()
                //어드민 계정만 접근 가능
                .antMatchers("/admin/**").hasRole("ADMIN")
                //로그인한 사용자 접근
                //.antMatchers().hasAnyRole("ADMIN","SELLER","CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //로그인 페이지 설정
                .loginPage("/login")
                //로그인 성공시 이동 경로
                .defaultSuccessUrl("http://localhost:3030",true)
                .permitAll()
                .and()
                .logout()
                //로그아웃 경로
                .logoutUrl("/logout")
                .logoutSuccessUrl("http://localhost:3030")
                .permitAll()
                .and()
                //권한 접근 불가 시 이동 페이지
                .exceptionHandling()
                .accessDeniedPage("https://naver.com");*/
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {

        System.out.println(dataSource);

        auth.jdbcAuthentication()
                //데이타 소스 연결
                .dataSource(dataSource)
                //비밀번호 암호화 반영
                .passwordEncoder(passwordEncoder())
                //인증처리
                .usersByUsernameQuery("select username, password, enabled "
                        + "from member_info "
                        + "WHERE username = ?")
                //권한처리
                .authoritiesByUsernameQuery("select m.username, r.authority "+
                        "from member_role mr " +
                        "inner join member_info m " +
                        "on mr.member_username = m.username " +
                        "inner join role r on mr.role_num = r.num " +
                        "WHERE m.username=? AND m.enabled=1 ");

    }

//Authentication = 로그인
//Authorization = 권한


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}






/*

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
*/

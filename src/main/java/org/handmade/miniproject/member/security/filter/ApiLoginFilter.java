/*
package org.handmade.miniproject.member.security.filter;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.dto.LoginDTO;
import org.handmade.miniproject.member.dto.MemberInfoDTO;
import org.handmade.miniproject.member.entity.MemberRefreshToken;
import org.handmade.miniproject.member.repository.MemberRefreshTokenRepository;
import org.handmade.miniproject.member.security.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {



    @Autowired
    private MemberRefreshTokenRepository memberRefreshTokenRepository;

    public ApiLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        log.info("=================================");
        log.info("===============attempt login==================");
        log.info("=================================");

        String jsonStr = request.getReader().readLine();

        Gson gson = new Gson();

        LoginDTO dto = gson.fromJson(jsonStr, LoginDTO.class);


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPw());

        Authentication authResult =  this.getAuthenticationManager().authenticate(authenticationToken);

        log.info(this.getAuthenticationManager().getClass().getName());

        log.info(authResult);


        return authResult;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("success login after.................");

        Object principal = authResult.getPrincipal();

        log.info(principal);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> map = new HashMap<>();

        String username = ((MemberInfoDTO)principal).getUsername();

        try {
            String jwt = new JWTUtil().generateToken(username);

            String refreshStr = "" + System.currentTimeMillis();

            map.put("TOKEN",jwt);
            map.put("REFRESH", refreshStr);

            long expireDate = System.currentTimeMillis() + (1000*60*60*30);

            MemberRefreshToken refreshToken = MemberRefreshToken.builder().username(username).refreshStr(refreshStr).expireDate(expireDate).build();

            memberRefreshTokenRepository.save(refreshToken);

            Gson gson = new Gson();
            String str = gson.toJson(map);

            response.getWriter().println(str);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}








*/

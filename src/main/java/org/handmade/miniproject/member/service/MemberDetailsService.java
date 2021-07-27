/*
package org.handmade.miniproject.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.dto.MemberDTO;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.member.repository.MemberInfoRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberInfoRepository memberInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("===================================");
        log.info("username: " + username);

        Optional<MemberInfo> op = memberInfoRepository.findByEmail(username);

        if(op.isPresent()){

            MemberInfo memberinfo = op.get();
            UserDetails result = new MemberDTO(
                    username,
                    memberinfo.getUserPwd(),
                    memberinfo.getMemberRoleSet().stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toList()) );

            return result;
        }

        return null;
    }
}
*/

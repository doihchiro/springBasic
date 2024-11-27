package com.oasis.acquiesce.security;

import com.oasis.acquiesce.domain.Member;
import com.oasis.acquiesce.mappers.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("===================");
        log.info("username: " + username);
        log.info(memberMapper);
        log.info("===================");

        Member member = memberMapper.selectMember(username);

        log.info("member: " + member);

        if (member == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return member;

    }
}

package com.oasis.acquiesce.securiry;

import com.oasis.acquiesce.domain.Member;
import com.oasis.acquiesce.domain.MemberAuth;
import com.oasis.acquiesce.mappers.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j2
public class MemberTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    MemberMapper memberMapper;

    @Test
    void testInsert() {
        for (int i = 1; i <= 100; i++) {
            Member member = new Member();
            member.setUid("user"+i);
            member.setPwd(passwordEncoder.encode("1111"));
            member.setUname("noel"+i);
            member.setEmail("user"+i+"@oasis.com");

            List<MemberAuth> authList = new ArrayList<>();
            authList.add(new MemberAuth("ROLE_USER", "user"+i));

            if (i >= 50) {
                authList.add(new MemberAuth( "ROLE_MANAGER", "user"+i));
            }

            if (i >= 90) {
                authList.add(new MemberAuth( "ROLE_ADMIN", "user"+i));
            }

            member.setAuthList(authList);

            memberMapper.insertMember(member);

            authList.stream().forEach(auth -> {
                memberMapper.insertAuth(auth);
            });

        }
    }

    @Test
    void testSelectMember() {

        String uid = "user99";

        Member member = memberMapper.selectMember(uid);
        log.info("member: " + member.getAuthList());
    }
}

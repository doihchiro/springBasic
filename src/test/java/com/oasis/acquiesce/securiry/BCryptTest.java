package com.oasis.acquiesce.securiry;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j2
public class BCryptTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void test1() {
        String str = "1111";

        String enStr = passwordEncoder.encode(str);
        log.info("encode: " + enStr);

        boolean result = passwordEncoder.matches(str, enStr);
        log.info("match: " + result);
    }
}
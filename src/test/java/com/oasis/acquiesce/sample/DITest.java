package com.oasis.acquiesce.sample;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/servlet-context.xml")
public class DITest {

    @Autowired
    Restaurant restaurant;

    @Autowired
    DataSource dataSource;

    @Test
    public void testCon() throws Exception {

        Connection con = dataSource.getConnection();

        System.out.println("con = " + con);

        con.close();
    }

    @Test
    public void testExist() {

        System.out.println("restaurant = " + restaurant);
    }
}

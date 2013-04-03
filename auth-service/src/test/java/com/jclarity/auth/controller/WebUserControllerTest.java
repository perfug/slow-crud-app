package com.jclarity.auth.controller;

import static org.junit.Assert.assertTrue;

import java.lang.management.ManagementFactory;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jclarity.crud_common.api.AuthServicePerformanceVariablesMXBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml", "file:src/test/resources/webmvc-test-config.xml" })
public class WebUserControllerTest {

    @Autowired private WebUserController controller;

    @Test
    public void sleepsForSpecifiedTime() throws MalformedObjectNameException {
        ObjectName name = new ObjectName(AuthServicePerformanceVariablesMXBean.ADDRESS);
        MBeanServer connection = ManagementFactory.getPlatformMBeanServer();
        AuthServicePerformanceVariablesMXBean variables = JMX.newMBeanProxy(connection, name, AuthServicePerformanceVariablesMXBean.class);

        checkPauseTime(variables, 100);
        checkPauseTime(variables, 200);
    }

    private void checkPauseTime(AuthServicePerformanceVariablesMXBean variables, int pauseTime) {
        variables.setSleepTime(pauseTime);
        long time = System.currentTimeMillis();
        controller.jsonFindWebUsersByUsernameEquals("foo");
        long delta = System.currentTimeMillis() - time;
        assertTrue(delta >= pauseTime);
    }

}

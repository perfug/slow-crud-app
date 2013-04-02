package com.jclarity.auth.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jclarity.auth.domain.WebUser;

@Component
public class FixtureService implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (WebUser user : WebUser.findAllWebUsers()) {
            user.remove();
        }
        createUser();
    }

    @Transactional
    private void createUser() {
        WebUser user = new WebUser();
        user.setUsername("foo");
        user.setPassword("bar");
        user.persist();
    }

}

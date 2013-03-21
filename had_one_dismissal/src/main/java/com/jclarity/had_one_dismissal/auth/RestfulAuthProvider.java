package com.jclarity.had_one_dismissal.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import com.jclarity.had_one_dismissal.entities.entities.WebUser;

public class RestfulAuthProvider implements UserDetailsService {

    RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        WebUser user = restTemplate.getForObject("http://127.0.0.1:9876/user/"+userName, WebUser.class);
        return user;
    }
    
    
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
}

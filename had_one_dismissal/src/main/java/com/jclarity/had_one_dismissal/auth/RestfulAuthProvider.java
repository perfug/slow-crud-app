package com.jclarity.had_one_dismissal.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import com.jclarity.had_one_dismissal.auth.domain.WebUser;

public class RestfulAuthProvider implements UserDetailsService {
    protected static final Log logger = LogFactory.getLog(RestfulAuthProvider.class);

    private RestTemplate restTemplate;
    public static String URL = "http://localhost:9876/auth/webusers";
    
    public static WebUser getUser(String user, RestTemplate restTemplate) {
        try {
            return restTemplate.getForObject(URL + "?find=ByUsernameEquals&username=" + user, WebUser.class);
        } catch (Exception e) {
            logger.debug("failed to get user", e);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return getUser(userName, restTemplate);
    }
    
    
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
}

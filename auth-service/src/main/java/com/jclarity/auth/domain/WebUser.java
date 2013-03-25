package com.jclarity.auth.domain;

import java.util.Collection;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findWebUsersByUsernameEqualsAndPasswordEquals", "findWebUsersByUsernameEquals" })
@RooJson
public class WebUser implements UserDetails {

    private String username;

    private String password;

    @Autowired
    @Transient
    private transient PasswordEncoder passwordEncoder;

    public void setPassword(String password) {
        if (password == null || password.equals("")) return;
        String encodedPassword = passwordEncoder.encodePassword(password, null);
        this.password = encodedPassword;
    }

    @Override
    public Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}

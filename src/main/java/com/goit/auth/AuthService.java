/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-23
 */
@Service
public class AuthService {

    public boolean hasAuthority(String name) {
        return getUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(it -> it.equals(name));
        /*for (GrantedAuthority authority: getUser().getAuthorities()) {
            if(name.equals(authority.getAuthority())) return true;
        }
        return false;*/
    }

    public String getUserName() {
        return getUser().getUsername();
    }

    private User getUser(){
        return (User) getAuthentication().getPrincipal();
    }

    private Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }
}

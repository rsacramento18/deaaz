package com.deaaz.deaazcore.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean isAuthenticated () {
        SecurityContextHolder.getContext().getAuthentication();
        return (SecurityContextHolder.getContext().getAuthentication() == null);
    }
}

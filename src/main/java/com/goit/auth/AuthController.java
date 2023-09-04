/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-22
 */
@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping("/profile")
    public ModelAndView get() {
        ModelAndView result = new ModelAndView("auth-page");
        result.addObject("username", authService.getUserName());
        return result;
        /*ModelAndView result = new ModelAndView("auth-page");
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("authentication.getCredentials() = " + authentication.getCredentials());
        String username = authentication.getName();
        result.addObject("username", username);

        User principal = (User) authentication.getPrincipal();
        System.out.println("principal.getUsername() =" + principal.getUsername());
        System.out.println("principal.getAuthorities() = " +  principal.getAuthorities());
        //System.out.println(principal.getClass());
        System.out.println("Thread.currentThread().getId() = " + Thread.currentThread().getId());
        return result;*/
    }

    @GetMapping("/supperadmin")
    public ModelAndView supperAdminOnly() {
        if(!authService.hasAuthority("admin")) {
            return new ModelAndView("forbidden");
        }
        return new ModelAndView("supperadmin");
    }
}

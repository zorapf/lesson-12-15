/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-03
 */
@Controller
@RequestMapping("/cookie")
public class CookieController {
    public static final String UID_COOKIE_NAME = "uid";

    @GetMapping
    public ModelAndView get(@CookieValue(value = UID_COOKIE_NAME, required = false) String uid,
                            HttpServletResponse response) {
        ModelAndView result = new ModelAndView("cookie");
        result.addObject("cookieValue", uid);

        if(uid == null) {
            response.addCookie(new Cookie(UID_COOKIE_NAME, UUID.randomUUID().toString()));
        }
        return result;

    }
}

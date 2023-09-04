/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.user;

import org.springframework.stereotype.Service;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-13
 */
@Service
public class UserValidateService {
    public boolean isEmailValid(String email) {
        if(email==null) {
            return false;
        }
        if(email.isBlank()) {
            return false;
        }
        if(email.strip().length() > 255) {
            return false;
        }
        if(!email.contains("@")) {
            return false;
        }
        return true;
    }

    public boolean isSearchQueryValid(String query) {
        if(query == null || query.isBlank()) return false;
        return true;
    }
}

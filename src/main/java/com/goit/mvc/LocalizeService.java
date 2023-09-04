/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.mvc;

import org.springframework.stereotype.Service;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-03
 */
@Service
public class LocalizeService {
    public String getCurrentTimeLabel(String language) {
        if("uk".equals(language)) {
            return "Поточний час";
        }
        return "Current Time";
    }
}

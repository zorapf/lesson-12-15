/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.mvc;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-07-30
 */
@Service
public class CurrentTimeService {
    public String getCurrentTime(String timezone) {
        return getCurrentTime(timezone,"yyyy.MM.dd hh:mm:ss");
    }

    public String getCurrentTime(String timezone, String format) {
        if(timezone == null) {
            return LocalDateTime.now().format(DateTimeFormatter
                    .ofPattern(format));
        }
        return ZonedDateTime
                .now(ZoneId.of(timezone))
                .format(DateTimeFormatter.ofPattern(format));

    }
}

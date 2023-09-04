/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.mvc;

import lombok.Data;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-07-31
 */
@Data
public class CurrentTimeRequest {
    private String timezone;
    private String format;
}

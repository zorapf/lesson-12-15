/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.user;

import lombok.Data;

import java.util.List;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-20
 */
@Data
public class UserInfo {
    private UserDTO user;
    private List<String> addresses;
}

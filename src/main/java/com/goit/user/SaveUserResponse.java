/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.user;

import lombok.Builder;
import lombok.Data;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-12
 */
@Data
@Builder
public class SaveUserResponse {
    private boolean success;
    private Error error;

    public enum Error {
        ok,
        invalidEmail
    }

    public static SaveUserResponse success() {
        return SaveUserResponse.builder().success(true).error(Error.ok).build();
    }

    public static SaveUserResponse failed(Error error) {
        return SaveUserResponse.builder().success(false).error(error).build();
    }
}

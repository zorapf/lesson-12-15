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
public class DeleteUserResponse {
    private boolean success;
    private Error error;

    public enum Error {
        ok,
        userNotFound
    }

    public static DeleteUserResponse success() {
        return DeleteUserResponse.builder().success(true).error(Error.ok).build();
    }

    public static DeleteUserResponse failed(Error error) {
        return DeleteUserResponse.builder().success(false).error(error).build();
    }
}

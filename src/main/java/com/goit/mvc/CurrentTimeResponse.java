 /*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.mvc;

import lombok.Builder;
import lombok.Data;

 /**
 * @author Petro Zora / Javatar LLC
 * @version 2023-07-31
 */
@Builder
@Data
public class CurrentTimeResponse {
    private boolean success;
    private Error error;
    private String time;

    public enum Error {
        ok,
        invalidTimezone
    }

    public static CurrentTimeResponse success(String time) {
        return CurrentTimeResponse.builder().success(true).error(Error.ok).time(time).build();
    }

    public static CurrentTimeResponse failed(Error error) {
        return CurrentTimeResponse.builder().error(error).success(false).build();
    }


}

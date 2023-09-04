/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.mvc;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-04
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/time")
public class TimeApiController {
    private final CurrentTimeService timeService;

    @PostMapping
    public CurrentTimeResponse get(@RequestBody CurrentTimeRequest request) {
        try{
            String time = timeService.getCurrentTime(
                    request.getTimezone(),
                    request.getFormat()
            );
            return CurrentTimeResponse.success(time);

        }catch (Exception ex){
            return CurrentTimeResponse.failed(CurrentTimeResponse.Error.invalidTimezone);
        }
    }

    @GetMapping
    public CurrentTimeResponse get(@RequestParam(name = "timezone", required = false) String tz,
                                   HttpServletResponse response) {
        try{
            response.addHeader("Access-Control-Allow-Origin", "*");
            String time = timeService.getCurrentTime(tz);
            return CurrentTimeResponse.success(time);

        }catch (Exception ex){
            return CurrentTimeResponse.failed(CurrentTimeResponse.Error.invalidTimezone);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> download(@RequestParam(name = "tz", required = false) String timezone) {

        String currentTime = timeService.getCurrentTime(timezone);
        byte [] bytes = currentTime.getBytes(StandardCharsets.UTF_8);
        ByteArrayResource resource = new ByteArrayResource(bytes);

        String filename = "time" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +".json";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(bytes.length)
                .body(resource);
    }
}

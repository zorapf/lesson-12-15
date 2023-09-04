/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-07-30
 */
@RequiredArgsConstructor
@RequestMapping("/current-time")
@Controller
public class CurrentTimeController {

    private final CurrentTimeService service;
    private final LocalizeService localizeService;

    //@RequestMapping(value = "/current-time", method = RequestMethod.GET)
    @GetMapping("/get")
    public ModelAndView getCurrentTime(
            @RequestParam(required = false, name = "timezone", defaultValue = "UTC") String timezone) {
        ModelAndView result = new ModelAndView("current-time");
        result.addObject("time", service.getCurrentTime(timezone));
        return result;
    }

    @ResponseBody
    @GetMapping("/getAsString")
    public String getCurrentTimeAsString(
            @RequestParam(required = false, name = "timezone", defaultValue = "UTC") String timezone) {
        return service.getCurrentTime(timezone);
    }

    /*@ResponseBody
    @GetMapping("/getAsObject")
    public Object getCurrentTimeAsObject(
            @RequestParam(required = false, name = "timezone", defaultValue = "UTC") String timezone) {
        return Collections.singletonMap("time", service.getCurrentTime(timezone));
    }*/

    @ResponseBody
    @GetMapping("/getAsObject")
    public CurrentTimeResponse getCurrentTimeAsObject(
            @RequestParam(required = false, name = "timezone", defaultValue = "UTC") String timezone) {
        try {
                return CurrentTimeResponse.success(service.getCurrentTime(timezone));
        } catch (Exception ex) {
            ex.printStackTrace();
            return CurrentTimeResponse.failed(CurrentTimeResponse.Error.invalidTimezone);
        }

    }

    @PostMapping("/post-x-form-url-encoded")
    public ModelAndView postCurrentTimeXFormUrlEncoded(String timezone) {
        ModelAndView result = new ModelAndView("current-time");
        result.addObject("time", service.getCurrentTime(timezone));
        return result;
    }

    @PostMapping("/post-json")
    public ModelAndView getCurrentTimeJson(@RequestBody CurrentTimeRequest request,
                                           @RequestHeader(value = "Accept-Language", required = false) String acceptLanguage) {
        ModelAndView result = new ModelAndView("current-time");
        result.addObject("time", service.getCurrentTime(
                request.getTimezone(), request.getFormat()));
        result.addObject("currentTimeLabel",
                localizeService.getCurrentTimeLabel(acceptLanguage));
        return result;
    }

    @GetMapping("/{timezone}/{format}")
    public ModelAndView getPathVariableTime(
            @PathVariable("timezone") String timezone,
            @PathVariable("format") String format) {
        ModelAndView result = new ModelAndView("current-time");
        result.addObject("time", service.getCurrentTime(timezone, format));
        result.addObject("currentTimeLabel",
                localizeService.getCurrentTimeLabel("uk"));
        return result;
    }
}

/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-12
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserValidateService validateService;

    @GetMapping("/list")
    public List<UserDTO> list() {
        return userService.findAll()
                .stream()
                .map(UserDTO::fromUser)
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public SaveUserResponse save(@RequestBody UserDTO userDTO) {
        if(!validateService.isEmailValid(userDTO.getEmail())) {
            return SaveUserResponse.failed(SaveUserResponse.Error.invalidEmail);
        }
        User user = UserDTO.fromDto(userDTO);
        userService.save(user);
        return SaveUserResponse.success();
    }

    @PostMapping("/delete/{email}")
    public DeleteUserResponse delete(@PathVariable("email") String email, HttpServletResponse response) {
        if(!userService.exists(email)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return DeleteUserResponse.failed(DeleteUserResponse.Error.userNotFound);
        }
        userService.deleteById(email);
        return DeleteUserResponse.success();
    }

    @PostMapping("/deleteAll")
    public DeleteUserResponse deleteAll(@RequestBody List<String> emails) {
        boolean thereAreNotExistingEmails = emails == null || emails.stream().noneMatch(email -> userService.exists(email));
        if(thereAreNotExistingEmails) {
            return DeleteUserResponse.failed(DeleteUserResponse.Error.userNotFound);
        }
        userService.deleteByIds(emails);
        return DeleteUserResponse.success();
    }

    @GetMapping("/search")
    public List<UserDTO> search(@RequestParam(name = "query", required = false) String query,
                                HttpServletResponse response) {
        if(!validateService.isSearchQueryValid(query)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Collections.emptyList();
        }
        return userService.search(query).stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }

    @GetMapping("/countOlderThen/{age}")
    public int countPeopleOlderThen(@PathVariable("age") int age) {
        return userService.countPeopleOlderThen(age);
    }

    @GetMapping("/info/{email}")
    public UserInfo getUserInfo(@PathVariable("email") String email) {
        return userService.getUserInfo2(email);
    }

    @GetMapping("/between")
    public List<User> getUserBetween(@RequestParam("start") String start,
                                     @RequestParam("end") String end) {
        return userService.getUserBetween(LocalDate.parse(start), LocalDate.parse(end));
    }
}

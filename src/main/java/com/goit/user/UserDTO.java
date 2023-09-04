/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-12
 */
@Builder
@Data
public class  UserDTO {
    private String email;
    private String fullName;
    private LocalDate birthday;
    private int age;
    private Gender gender;

    public static UserDTO fromUser(User user) {
        int age = (int) ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now());
        return UserDTO.builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .birthday(user.getBirthday())
                .age(age)
                .gender(user.getGender())
                .build();
    }

    public static User fromDto(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setBirthday(userDTO.getBirthday());
        user.setGender(userDTO.getGender());
        return user;
    }
}

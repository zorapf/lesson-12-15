/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.user;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-08-12
 */
@Data
@Table(name = "user")
@Entity
public class User {

    @Id
    private String email;

    private String fullName;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String password;

    private String authority;

}

/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-07-29
 */
@Component
public class TestComponent {
    public TestComponent() {
        System.out.println("In constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("In PostConstruct");
    }

    @PreDestroy
    public void onDistroy() {
        System.out.println("In onDistroy");
    }

}

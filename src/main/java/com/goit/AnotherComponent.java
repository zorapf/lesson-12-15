/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-07-29
 */
@RequiredArgsConstructor
@Component
public class AnotherComponent {
    private final ChildComponent childComponent;

    @PostConstruct
    public void init(){
        childComponent.hello();
    }
}

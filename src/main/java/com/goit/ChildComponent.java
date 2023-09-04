/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-07-29
 */
//@Scope("singleton") //не міняє
// @Scope("prototype") // всі компоненти
@Component
public class ChildComponent {

    public ChildComponent() {
        System.out.println("In ChildComponent Constructor ");
    }

    public void hello() {
        System.out.println("Hello, I am ChildComponent");
    }
}

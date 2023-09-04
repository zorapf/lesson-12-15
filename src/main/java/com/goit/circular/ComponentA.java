/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.circular;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Petro Zora / Javatar LLC
 * @version 2023-07-30
 */

@Service
public class ComponentA {
    private final ApplicationContext context;

    public ComponentA(ApplicationContext context) {
        this.context = context;
        System.out.println("Creating A");
    }

    public void print(){
        System.out.println("I am componentA");
    }

    private ComponentB getComponentB() {
        return context.getBeansOfType(ComponentB.class)
                .values()
                .stream()
                .findFirst().orElse(null);
    }
}

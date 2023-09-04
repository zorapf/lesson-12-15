/*
 * Copyright (c) 2023 Javatar LLC
 * All rights reserved.
 */

package com.goit.circular;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@DependsOn("componentA")
@Service
public class ComponentB {
    private final ApplicationContext context;

    public ComponentB(ApplicationContext context) {
        this.context = context;
        System.out.println("Creating B");
    }

    @PostConstruct
    public void init(){
        getComponentA().print();
    }

    private ComponentA getComponentA() {
        return context.getBeansOfType(ComponentA.class)
                .values()
                .stream()
                .findFirst().orElse(null);
    }
}

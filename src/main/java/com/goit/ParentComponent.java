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
public class ParentComponent {
    //@Autowired var 1
    private final ChildComponent childComponent;

    //var2 - supper
    /*public ParentComponent(ChildComponent childComponent) {
        this.childComponent = childComponent;
    }*/

    /*@Autowired // var 3
    public void setChildComponent(ChildComponent childComponent) {
        this.childComponent = childComponent;
    }*/

    @PostConstruct
    public void init(){
        childComponent.hello();
    }
}

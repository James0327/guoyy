package com.jw.james.tcly.envcopy;

import com.google.common.collect.Sets;
import lombok.Data;

import java.util.Set;

/**
 * Description: guoyy
 * com.jw.tcly.envcopy.Node
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/20 23:22
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Data
public class Node {
    private String id;
    private Set<String> parent;
    private Set<String> chriden;

    public Node(String id) {
        this.id = id;
        this.parent = Sets.newHashSet();
        this.chriden = Sets.newHashSet();
    }

}

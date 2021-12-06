package com.jw.anno;

import com.jw.enums.FileTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: guoyy
 * com.jw.anno.TableFileType
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/11/30 17:43
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TableFileType {

    FileTypeEnum[] value();

}

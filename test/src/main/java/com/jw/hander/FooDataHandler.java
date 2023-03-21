package com.jw.hander;

import com.jw.DataLoadHandler;
import com.jw.anno.TableFileType;
import com.jw.enums.FileTypeEnum;
import org.springframework.stereotype.Component;

/**
 * Description: atpco_fuel_cost
 * T173DataHandler
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/5/20 22:15
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@TableFileType({FileTypeEnum.FOO, FileTypeEnum.YQYR})
@Component
public class FooDataHandler implements DataLoadHandler {

    @Override
    public boolean load() {
        return false;
    }

}

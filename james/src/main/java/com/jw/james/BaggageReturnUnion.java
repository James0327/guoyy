package com.jw.james;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: test com.jw.BaggageReturnUnion
 * @Package: com.jw
 * @ClassName: BaggageReturnUnion
 * @Author: james.guo
 * @Date: 2019/3/18 22:48
 * @Version: 1.0
 */
public class BaggageReturnUnion {
    List<BaggageReturn> baggageReturnList = new ArrayList<>();

    public List<BaggageReturn> getBaggageReturnList() {
        return baggageReturnList;
    }

    public void setBaggageReturnList(List<BaggageReturn> baggageReturnList) {
        this.baggageReturnList = baggageReturnList;
    }
}
